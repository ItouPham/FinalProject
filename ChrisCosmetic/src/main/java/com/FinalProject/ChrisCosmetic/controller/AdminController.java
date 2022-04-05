package com.FinalProject.ChrisCosmetic.controller;

import javax.validation.Valid;

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
        return "add-new-user";
    }

    @PostMapping("/account/add")
    public String save(@ModelAttribute("account") @Valid AccountDTO accountDTO,
                       BindingResult result, RedirectAttributes redirect) {
        // check if the account existed
        Account accExist = accountService.findByEmail(accountDTO.getEmail());
        if (accExist != null && !accExist.getId().equals(accountDTO.getId())) {
            result.addError(new FieldError("account", "email", accountDTO.getEmail(), true, null, null, "Email address already in use"));
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
        return "add-new-user";
    }

    @GetMapping("/account/delete/{id}")
    public String delete(@PathVariable Long id) {
        accountService.delete(id);
        return "redirect:/admin/account";
    }

}
