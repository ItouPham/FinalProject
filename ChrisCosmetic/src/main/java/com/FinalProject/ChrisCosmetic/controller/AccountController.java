package com.FinalProject.ChrisCosmetic.controller;

import javax.validation.Valid;

import com.FinalProject.ChrisCosmetic.dto.AccountDTO;
import com.FinalProject.ChrisCosmetic.entity.Role;
import com.FinalProject.ChrisCosmetic.repository.RoleRepository;
import com.FinalProject.ChrisCosmetic.service.RoleService;
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
import com.FinalProject.ChrisCosmetic.service.AccountService;

import java.util.HashSet;
import java.util.UUID;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
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

        if (accountDTO.getPassword() == null || accountDTO.getPassword().length() == 0) {
            result.addError(
                    new FieldError("account", "password", "Password can not empty"));
        } else if (accountDTO.getPassword().length() < 6) {
            result.addError(
                    new FieldError("account", "password", "Password must be at least 6 characters"));
        }

        if (accountDTO.getPassword() != null && accountDTO.getConfirmPassword() != null){
            if(!accountDTO.getPassword().equals(accountDTO.getConfirmPassword())){
                result.addError(
                        new FieldError("account","confirmPassword","Password and Confirm password not match")
                );
            }

        }

        if (result.hasErrors()) {
            return "/register";
        }

        HashSet<Role> roles = new HashSet<>();
        roles.add(roleService.findRoleByRoleName("CUSTOMER"));
        accountDTO.setRoles(roles);
        accountDTO.setId(UUID.randomUUID().toString());
        String encodedPassword = bCryptPasswordEncoder.encode(accountDTO.getPassword());
        accountDTO.setPassword(encodedPassword);
        accountService.save(accountDTO);

        redirect.addFlashAttribute("successMessage", "Register account successfully");
        return "redirect:/login";
    }

}
