package com.FinalProject.ChrisCosmetic.controller;

import javax.validation.Valid;

import com.FinalProject.ChrisCosmetic.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.FinalProject.ChrisCosmetic.entity.Account;
import com.FinalProject.ChrisCosmetic.repository.AccountRepository;
import com.FinalProject.ChrisCosmetic.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String signup(Model model) {
        model.addAttribute("account", new AccountDTO());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("account") @Valid AccountDTO accountDTO,
                                  BindingResult result, RedirectAttributes redirect) {
        // check if the account existed
        Account accExist = accountService.findByEmail(accountDTO.getEmail());
        if (accExist != null) {
            result.addError(new FieldError("account", "email", "Email address already in use"));
        }

        // check if password and confirm password not match
        if (accountDTO.getPassword() != null && accountDTO.getConfirmPassword() != null) {
            if (!accountDTO.getPassword().equals(accountDTO.getConfirmPassword())) {
                result.addError(
                        new FieldError("account", "confirmPassword", "Password and Confirm password not match"));
            }
        }

        if (result.hasErrors()) {
            return "/register";
        }

        String encodedPassword = bCryptPasswordEncoder.encode(accountDTO.getPassword());
        accountDTO.setPassword(encodedPassword);
        accountService.save(accountDTO);

        redirect.addFlashAttribute("successMessage", "Register account successfully");
        return "redirect:/login";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
