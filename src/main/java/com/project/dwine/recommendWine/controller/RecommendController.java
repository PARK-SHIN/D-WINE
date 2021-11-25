package com.project.dwine.recommendWine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dwine.hashtag.model.vo.Hashtag;
import com.project.dwine.recommendWine.model.service.RecommendService;

@Controller
@RequestMapping("/recommendWine")
public class RecommendController {
	
	private RecommendService recommendService;
	
	@Autowired
	public RecommendController(RecommendService recommendService) {
		this.recommendService = recommendService;
	}
	
	@GetMapping("main")
	public String recommendMain(Model model) {
		
		List<Hashtag> moodList = recommendService.selectHashtagList(1);
		List<Hashtag> foodList = recommendService.selectHashtagList(2);
		
		model.addAttribute("moodList", moodList);
		model.addAttribute("foodList", foodList);
		
		return "/recommendWine/main";
		
	}

	
}
