package com.project.dwine.faq.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.faq.model.service.FaqService;
import com.project.dwine.faq.model.vo.Faq;

@Controller
@RequestMapping("/faq")
public class FaqController {

	private FaqService faqService;
	
	@Autowired
	public FaqController(FaqService faqService) {
		this.faqService = faqService;
	}
	
	@GetMapping("list")
	public ModelAndView selectFaqList(ModelAndView mv) {
		
		List<Faq> faqList = faqService.selectFaqList();
		
		mv.addObject("faqList", faqList);
		mv.setViewName("faq/list");
		
		return mv;
	}
	
	@PostMapping(value = "delete")
	@ResponseBody
	public void deleteOrder(HttpServletResponse response, @RequestParam(value = "checkArr[]") List<String> checkArr) throws IOException {
		List<Integer> arr = new ArrayList<>();
		
		for(String s : checkArr) {
			arr.add(Integer.parseInt(s));
		}
		
		int results = 0;
		for(int faqNo : arr) {
			results += faqService.deleteFaq(faqNo);
		}
		
		if(arr.size() == results) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
		}
	} 
}
