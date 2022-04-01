package com.FinalProject.ChrisCosmetic.controller;

import javax.validation.Valid;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FinalProject.ChrisCosmetic.repository.AccountRepository;
import com.FinalProject.ChrisCosmetic.service.AccountService;

import groovy.lang.Binding;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountService accountService;

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
		model.addAttribute("user", new User());
		return "add-new-user";
	}
	
	
	@GetMapping("/account/{id}/delete")
	public String delete(@PathVariable Long id) {
		accountService.delete(id);
		return "redirect:/account";
	}
	
//	@PostMapping("/users-list/save")
//	public String save(@Valid User user, BindingResult result) {
//		if (result.hasErrors()) {
//			return "user-management";
//		}
//		
//		userService.save(user);
//		return "redirect:/contact";
//		
//	}
	
}
