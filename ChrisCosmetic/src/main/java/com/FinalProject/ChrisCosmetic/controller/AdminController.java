package com.FinalProject.ChrisCosmetic.controller;

import javax.validation.Valid;

import com.FinalProject.ChrisCosmetic.dto.ProductDTO;
import com.FinalProject.ChrisCosmetic.entity.Product;
import com.FinalProject.ChrisCosmetic.entity.Role;
import com.FinalProject.ChrisCosmetic.entity.SubCategory;
import com.FinalProject.ChrisCosmetic.repository.RoleRepository;
import com.FinalProject.ChrisCosmetic.service.ProductService;
import com.FinalProject.ChrisCosmetic.service.RoleService;
import com.FinalProject.ChrisCosmetic.service.SubCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.FinalProject.ChrisCosmetic.dto.AccountDTO;
import com.FinalProject.ChrisCosmetic.entity.Account;
import com.FinalProject.ChrisCosmetic.service.AccountService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("")
    public String admin() {
        return "home-admin";
    }

    @GetMapping("/account")
    public String userList(Model model) {
        model.addAttribute("account", accountService.findAll());
        return "user-management";
    }

    @GetMapping("/account/add")
    public String add(Model model) {
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("roles", roleService.findAllRole());
        return "add-new-user";
    }

    @PostMapping("/account/add")
    public String save(@ModelAttribute("account") @Valid AccountDTO accountDTO,
                       BindingResult result, RedirectAttributes redirect) {
        // check if the account existed
        Account accExist = accountService.findByEmail(accountDTO.getEmail());
        if (accExist != null) {
            result.addError(new FieldError("account", "email", "Email address already in use"));
        }

        if (accountDTO.getPassword() == null || accountDTO.getPassword().length() == 0) {
            result.addError(
                    new FieldError("account", "password", "Password can not empty"));
        } else if (accountDTO.getPassword().length() < 6) {
            result.addError(
                    new FieldError("account", "password", "Password must be at least 6 characters"));
        }

        // check if password and confirm password not match
        if (accountDTO.getPassword() != null && accountDTO.getConfirmPassword() != null) {
            if (!accountDTO.getPassword().equals(accountDTO.getConfirmPassword())) {
                result.addError(
                        new FieldError("account", "confirmPassword", "Password and Confirm password not match"));
            }
        }

        if (result.hasErrors()) {
            return "/add-new-user";
        }

        HashSet<Role> roles = new HashSet<>();
        if (accountDTO.getRoleID().equals("1")) {
            roles.add(roleService.findRoleByRoleName("ADMIN"));
            accountDTO.setRoles(roles);
        } else if (accountDTO.getRoleID().equals("2")){
            roles.add(roleService.findRoleByRoleName("EMPLOYEE"));
            accountDTO.setRoles(roles);
        } else {
            roles.add(roleService.findRoleByRoleName("CUSTOMER"));
            accountDTO.setRoles(roles);
        }

        accountDTO.setId(UUID.randomUUID().toString());
        String encodedPassword = bCryptPasswordEncoder.encode(accountDTO.getPassword());
        accountDTO.setPassword(encodedPassword);
        accountService.save(accountDTO);
        redirect.addFlashAttribute("successMessage", "Save account successfully");
        return "redirect:/admin/account";
    }

    @GetMapping("/account/edit/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        model.addAttribute("account", accountService.findById(id));
        return "update-user";
    }

    @PostMapping("/account/edit")
    public String updateUser(@ModelAttribute("account") AccountDTO accountDTO,
                             BindingResult result, RedirectAttributes redirect) {

        if (accountDTO.getPassword() != null) {
            if (accountDTO.getPassword().length() < 6) {
                result.addError(
                        new FieldError("account", "password", "Password must be at least 6 characters"));
            } else {
                String encodedPassword = bCryptPasswordEncoder.encode(accountDTO.getPassword());
                accountDTO.setPassword(encodedPassword);
            }
        }
        if (result.hasErrors()) {
            return "/update-user";
        }

        HashSet<Role> roles = new HashSet<>();
        if (accountDTO.getRoleID().equals("1")) {
            roles.add(roleService.findRoleByRoleName("ADMIN"));
            accountDTO.setRoles(roles);
        } else if (accountDTO.getRoleID().equals("2")){
            roles.add(roleService.findRoleByRoleName("EMPLOYEE"));
            accountDTO.setRoles(roles);
        } else {
            roles.add(roleService.findRoleByRoleName("CUSTOMER"));
            accountDTO.setRoles(roles);
        }

        accountService.save(accountDTO);
        redirect.addFlashAttribute("successMessage", "Save account successfully");
        return "redirect:/admin/account";
    }

    @GetMapping("/account/delete/{id}")
    public String delete(@PathVariable String id) {
        accountService.delete(id);
        return "redirect:/admin/account";
    }

    @RequestMapping("/product")
    public String productList(Model model) {
        return listProductByPage(model, 1);
    }

    @GetMapping("/product/page/{pageNumber}")
            public String listProductByPage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Product> page = productService.findAllProduct(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("products", listProducts);
        return "product-management";
    }

    @GetMapping("/product/add")
    public String viewAddProductPage(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories", subCategoryService.findAllSubCategory());
        return "add-new-product";
    }

    @PostMapping("/product/add")
    public String processAddNewProduct(@ModelAttribute("product") ProductDTO productDTO,
                                       RedirectAttributes redirect,
                                       @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        productDTO.setProductImage(fileName);

        Product entity = new Product();
        BeanUtils.copyProperties(productDTO, entity);

        SubCategory subCategory = new SubCategory();
        subCategory.setId(productDTO.getSubCategoryID());
        entity.setSubCategory(subCategory);

        productService.save(entity);

        String uploadDir = "./product-images/" + entity.getId();

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new IOException("Could not save uploaded file: " + fileName);
        }
        redirect.addFlashAttribute("successMessage", "Add product successfully");
        return "redirect:/admin/product";
    }

    @GetMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Optional<Product> opt = productService.findById(id);
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("categories", subCategoryService.findAllSubCategory());
        if (opt.isPresent()) {
            Product entity = opt.get();
            BeanUtils.copyProperties(entity, productDTO);
            productDTO.setSubCategoryID(entity.getSubCategory().getId());
            model.addAttribute("product", productDTO);
            return "update-product";
        }
        model.addAttribute("message", "Product is not existed");
        return "redirect:/admin/product";
    }

    @PostMapping("/product/edit")
    public String processUpdateProduct(@ModelAttribute("product") ProductDTO productDTO,
                                       RedirectAttributes redirect,
                                       @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        productDTO.setProductImage(fileName);

        Product entity = new Product();
        BeanUtils.copyProperties(productDTO, entity);

        SubCategory subCategory = new SubCategory();
        subCategory.setId(productDTO.getSubCategoryID());
        entity.setSubCategory(subCategory);

        productService.save(entity);

        String uploadDir = "./product-images/" + entity.getId();

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new IOException("Could not save uploaded file: " + fileName);
        }
        redirect.addFlashAttribute("successMessage", "Update product successfully");
        return "redirect:/admin/product";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/product";
    }
}
