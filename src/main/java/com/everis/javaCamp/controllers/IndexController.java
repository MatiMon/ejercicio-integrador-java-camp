package com.everis.javaCamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class IndexController {
	
	@GetMapping("/")
	public String index() {
		return "home.html";
	}

}
