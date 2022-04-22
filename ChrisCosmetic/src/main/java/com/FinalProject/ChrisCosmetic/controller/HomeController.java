package com.FinalProject.ChrisCosmetic.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.Authenticator;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String viewHomePage() {
		return "index";
	}

}
