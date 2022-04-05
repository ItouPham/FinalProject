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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.FinalProject.ChrisCosmetic.entity.Account;
import com.FinalProject.ChrisCosmetic.repository.AccountRepository;
import com.FinalProject.ChrisCosmetic.service.AccountService;

@Controller
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

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
    public String processRegister(@Valid Account account, BindingResult result, RedirectAttributes redirect) {

	// check if the account existed
	if (accountRepository.findByEmail(account.getEmail()) != null) {
	    result.addError(new FieldError("Account", "email", "Email address already in use"));
	}

	// check if password and confirm password not match
	if (account.getPassword() != null && account.getConfirmPassword() != null) {
	    if (!account.getPassword().equals(account.getConfirmPassword())) {
		result.addError(
			new FieldError("Account", "confirmPassword", "Password and Confirm password not match"));
	    }
	}

	if (result.hasErrors()) {
	    return "/register";
	}

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String encodedPassword = encoder.encode(account.getPassword());
	account.setPassword(encodedPassword);
	String encodedConfirmPassword = encoder.encode(account.getConfirmPassword());
	account.setConfirmPassword(encodedConfirmPassword);
	accountRepository.save(account);

	redirect.addFlashAttribute("successMessage", "Register account successfully");
	return "redirect:/login";
    }

    @RequestMapping("/test")
    public String test() {
	return "test";
    }
}
