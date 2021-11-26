package com.project.dwine.salesInquiry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.salesInquiry.model.service.SalesInquiryService;
import com.project.dwine.salesInquiry.model.vo.Total;

@Controller
@RequestMapping("/salesInquiry")
public class SalesInquiryController {
	
	private SalesInquiryService salesInquiryService;
	
	@Autowired
	public SalesInquiryController(SalesInquiryService salesInquiryService) {
		this.salesInquiryService = salesInquiryService;
	}

	@GetMapping("list")
	public ModelAndView selectTodayStatus(ModelAndView mv) {
		
		Total total = salesInquiryService.selectTodayStatus();

		mv.addObject("total", total);
		mv.setViewName("salesInquiry/list");
		
		return mv;
	}
	
	@GetMapping("daily")
	public Model dailyList(Model model) {
		
		List<Total> dailyList = salesInquiryService.dailyList();
		
		model.addAttribute("dailyList", dailyList);
		
		return model;
	}
	
	
}
