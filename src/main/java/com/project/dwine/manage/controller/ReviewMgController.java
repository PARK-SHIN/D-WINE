package com.project.dwine.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.manage.model.service.ReviewMgService;
import com.project.dwine.manage.model.vo.Report;

@Controller
@RequestMapping("/manage")
public class ReviewMgController {
	private ReviewMgService reviewMgService;
	
	@Autowired
	public ReviewMgController(ReviewMgService reviewMgService) {
		this.reviewMgService = reviewMgService;
	}
	
	
	@GetMapping("/reviewMg/main")
	public ModelAndView reportMgList(ModelAndView mv) {
		List<Report> reviewMgList = reviewMgService.selectReviewList();
		mv.addObject("reviewMgList", reviewMgList);
		mv.setViewName("manage/reviewMg/main");
		return mv;
	}
	
	

}
