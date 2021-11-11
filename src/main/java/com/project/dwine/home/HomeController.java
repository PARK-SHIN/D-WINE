package com.project.dwine.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	@GetMapping(value = { "/", "/home" })
	public String home() {
		return "home/home";
	}
	
	@PostMapping(value = "/")
	public String redirectMain() {
		return "redirect:/";
	}
}
