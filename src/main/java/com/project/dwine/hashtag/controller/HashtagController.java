package com.project.dwine.hashtag.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("hashList")
	@ResponseBody
	public Map<String, Object> selectHashtagList(){
		Map<String, Object> map = new HashMap<>();
		List<Hashtag> moodList = hashtagService.selectHashtagList(1);
		List<Hashtag> foodList = hashtagService.selectHashtagList(2);
		
		map.put("mood", moodList);
		map.put("food", foodList);
		
		return map;
	}
	
	@PostMapping("hashNameCheck") 
	public void hashNameCheck(HttpServletResponse response, @RequestParam String hashName) throws IOException {
		
		int result = hashtagService.hashNameCheck(hashName);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
	}
	
	@PostMapping("regist")
	public void registHashtag(HttpServletResponse response, @RequestParam String hashName, @RequestParam String hashType) throws IOException {
		response.setCharacterEncoding("UTF-8");
		
		int result = hashtagService.registHashtag(hashName, Integer.parseInt(hashType));		
	
		if(result > 0) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
		}
	}
	
	@PostMapping("modify")
	public void modifyHashtag(HttpServletResponse response, @RequestParam String hashNo, @RequestParam String hashName, @RequestParam String hashType) throws IOException {
		response.setCharacterEncoding("UTF-8");
		
		int result = hashtagService.modifyHashtag(Integer.parseInt(hashNo), hashName, Integer.parseInt(hashType));
		
		if(result > 0) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
		}
	}
	
	@PostMapping("delete")
	public void deleteHashtag(HttpServletResponse response, @RequestParam String hashNo) throws IOException {
		response.setCharacterEncoding("UTF-8");
		
		int result1 = hashtagService.checkDeleteHash(Integer.parseInt(hashNo));
		
		if(result1 > 0) {
			response.getWriter().print("impossible");
		} else {
			int result2 = hashtagService.deleteHashtag(Integer.parseInt(hashNo));
			
			if(result2 > 0) {
				response.getWriter().print("success");	
			} else {
				response.getWriter().print("fail");				
			}
		}
	}
	
	@PostMapping("selectByHashNo")
	@ResponseBody
	public Hashtag selectByHashNo(@RequestParam int hashNo) {
		return hashtagService.selectByHashNo(hashNo);
	}
}
