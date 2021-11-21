package com.project.dwine.hashtag.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dwine.hashtag.model.service.HashtagService;
import com.project.dwine.hashtag.model.vo.Hashtag;

@Controller
@RequestMapping("/hashtag")
public class HashtagController {
	
private HashtagService hashtagService;
	
	@Autowired
	public HashtagController(HashtagService hashtagService) {
		this.hashtagService = hashtagService;
	}
	
	@GetMapping("form")
	public void hashtagForm() {}
	
	@GetMapping(value="category", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> selectHashtagList(){
		Map<String, Object> map = new HashMap<>();
		List<Hashtag> moodList = hashtagService.selectHashtagList(1);
		List<Hashtag> foodList = hashtagService.selectHashtagList(2);
		
		map.put("mood", moodList);
		map.put("food", foodList);
		
		return map;
	}
	
	

}
