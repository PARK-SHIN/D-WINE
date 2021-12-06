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
	
	@GetMapping("privacy_policy")
	public String privacy_policy() {
		return "common/privacy_policy";
	}
	
	@GetMapping("use_policy")
	public String use_policy() {
		return "common/use_policy";
	}
}
