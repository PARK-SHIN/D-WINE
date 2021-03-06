package com.project.dwine.recommendWine.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@GetMapping("list")
	public String getWineList(@RequestParam String hashNo, @RequestParam String hashName, Model model) {
		
		List<Product> wineList = recommendService.selectWineList(Integer.parseInt(hashNo));
		model.addAttribute("wineList", wineList);
		model.addAttribute("hashName", hashName);
		
		return "/recommendWine/list"; 
	}
	
	@GetMapping("search")
	public String search(@RequestParam String search_hash, Model model) {
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
	
	@GetMapping("weather")
	public void weatherPage() {}

	@PostMapping("weather")
	@ResponseBody
	public Map<String, Object> selectProductByWeather(@RequestParam String current_temp) {
		
		int temp = Integer.parseInt(current_temp);
		String message = "";
		
		Map<String, Object> map = new HashMap<>();
		List<Product> productList = null;
		
		
		if(temp >= 20) { // ?????? typeNo : 3(????????????), varietyNo : 5(?????? ??????), 14(?????????) 
			productList = recommendService.selectProductByWeather(3, 5, 14);  
			message = "???????????? ????????? ?????? ??????";
		}  else if(temp > 5) { // ??? & ?????? typeNo : 2(?????????), varietyNo : 2(?????????/??????), 5(?????? ??????)
			productList = recommendService.selectProductByWeather(2, 2, 5);
			message = "????????? ?????? ????????? ?????? ??????";
		} else { // ?????? typeNo : 1(??????), varietyNo : 1(???????????? ?????????), 2(?????????/??????)
			productList = recommendService.selectProductByWeather(1, 18, 19);
			message = "????????? ????????? ?????? ??????";
		}		

		if(!productList.isEmpty()) {
			int random = (int)(Math.random() * (productList.size()));
			Product product = productList.get(random);
			
			map.put("product", product);
			map.put("message", message);
			
		} else {
			message = "?????? ??????????????????.";
			map.put("message", message);
		}
		

		return map;
	}
}
