package com.FinalProject.ChrisCosmetic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FinalProject.ChrisCosmetic.entity.Account;
import com.FinalProject.ChrisCosmetic.repository.AccountRepository;
import com.FinalProject.ChrisCosmetic.service.AccountService;

@Controller
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

//    @Autowired
//    public AccountController(AccountService accountService) {
//	this.accountService = accountService;
//    }

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
	model.addAttribute("account", new Account());
	return "register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid Account account, BindingResult result) {

	// check if the account existed
//	if (accountService.accountExisted(account.getEmail())) {
//	    result.addError(new FieldError("Account", "email", "Email address already in use"));
//	}
//	if (result.hasErrors()) {
//	    return "/register";
//	}
	// check if password and confirm password not match
//	if (account.getPassword() != null && account.getConfirmPassword() != null) {
//	    if (!account.getPassword().equals(account.getConfirmPassword())) {
//		result.addError(
//			new FieldError("account", "confirmPassword", "Password and Confirm password not match"));
//	    }
//	}
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String encodedPassword = encoder.encode(account.getPassword());
	account.setPassword(encodedPassword);
	String encodedConfirmPassword = encoder.encode(account.getConfirmPassword());
	account.setConfirmPassword(encodedConfirmPassword);
	accountRepository.save(account);
	return "redirect:/login";
    }

    @RequestMapping("/test")
    public String test() {
	return "test";
    }
}
