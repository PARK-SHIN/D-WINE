package com.project.dwine.recommendWine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dwine.hashtag.model.vo.Hashtag;
import com.project.dwine.product.model.vo.Product;
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
	
	@PostMapping("list")
	public String wineList(@RequestParam String hashNo, @RequestParam String hashName, Model model) {
		
		List<Product> wineList = recommendService.selectWineList(Integer.parseInt(hashNo));
		model.addAttribute("wineList", wineList);
		model.addAttribute("hashName", hashName);
		
		return "/recommendWine/list"; 
	}

	@PostMapping("search")
	public String searchWineList(@RequestParam String search_hash, Model model) {
		String hashName = "";
		
		if(search_hash.contains("#")) {
			hashName = search_hash.substring(1);
			model.addAttribute("hashName", search_hash);
		} else {
			hashName = search_hash;
			model.addAttribute("hashName", "#" + search_hash);
		}
		
		List<Product> wineList = recommendService.searchWineList(hashName);
		
		model.addAttribute("wineList", wineList);	
		
		return "/recommendWine/list"; 
	}
	
}
