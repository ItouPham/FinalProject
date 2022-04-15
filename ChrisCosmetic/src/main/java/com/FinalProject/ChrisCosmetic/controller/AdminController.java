package com.FinalProject.ChrisCosmetic.controller;

import javax.validation.Valid;

import com.FinalProject.ChrisCosmetic.dto.ProductDTO;
import com.FinalProject.ChrisCosmetic.service.RoleService;
import com.FinalProject.ChrisCosmetic.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.FinalProject.ChrisCosmetic.dto.AccountDTO;
import com.FinalProject.ChrisCosmetic.entity.Account;
import com.FinalProject.ChrisCosmetic.service.AccountService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SubCategoryService subCategoryService;

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

        String encodedPassword = bCryptPasswordEncoder.encode(accountDTO.getPassword());
        accountDTO.setPassword(encodedPassword);
        accountService.save(accountDTO);
        redirect.addFlashAttribute("successMessage", "Save account successfully");
        return "redirect:/admin/account";
    }

    @GetMapping("/account/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
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
        accountService.save(accountDTO);
        redirect.addFlashAttribute("successMessage", "Save account successfully");
        return "redirect:/admin/account";
    }

    @GetMapping("/account/delete/{id}")
    public String delete(@PathVariable Long id) {
        accountService.delete(id);
        return "redirect:/admin/account";
    }

    @GetMapping("/product")
    public String productList(){
        return "product-management";
    }

    @GetMapping("/product/add")
    public String viewAddProductPage(Model model){
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories", subCategoryService.findAllSubCategory());
        return "add-new-product";
    }
}
