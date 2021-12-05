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
		
		
		if(temp >= 20) { // 여름 typeNo : 3(스파클링), varietyNo : 5(피노 누아), 14(리슬링) 
			productList = recommendService.selectProductByWeather(3, 5, 14);  
			message = "시원하게 즐기면 좋을 와인";
		}  else if(temp > 5) { // 봄 & 가을 typeNo : 2(화이트), varietyNo : 2(메를로/멜롯), 5(피노 누아)
			productList = recommendService.selectProductByWeather(2, 2, 5);
			message = "사계절 내내 즐기기 좋은 와인";
		} else { // 겨울 typeNo : 1(레드), varietyNo : 1(카베르네 소비뇽), 2(메를로/멜롯)
			productList = recommendService.selectProductByWeather(1, 18, 19);
			message = "뱅쇼로 즐기면 좋을 와인";
		}		

		if(!productList.isEmpty()) {
			int random = (int)(Math.random() * (productList.size()));
			Product product = productList.get(random);
			
			map.put("product", product);
			map.put("message", message);
			
		} else {
			message = "상품 준비중입니다.";
			map.put("message", message);
		}
		

		return map;
	}
}
