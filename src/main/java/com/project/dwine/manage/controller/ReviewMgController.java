package com.project.dwine.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.manage.model.service.ReviewMgService;
import com.project.dwine.manage.model.vo.Report;
import com.project.dwine.manage.model.vo.Review;

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
		List<Review> reviewMgList = reviewMgService.selectReviewList();
		mv.addObject("reviewMgList", reviewMgList);
		mv.setViewName("manage/reviewMg/main");
		return mv;
	}
	

	   @GetMapping("reviewMg/delete")
	   public String reviewMgdelete(String review_no) throws Exception {
		   reviewMgService.deleteReviewMg(review_no);
		   
	      return "redirect:/manage/memberMg/main";
	   }

	   
	   @ResponseBody
	   @RequestMapping(value = "/reviewMg/delete")
	   public String reviewMgMultiDelte(HttpServletRequest request, HttpServletResponse response) throws Exception {

	       String[] meberDeleteArr = request.getParameterValues("valueArr");
	       
	       int size = meberDeleteArr.length;
	       for(int i = 0; i < size; i++) {
	    	   reviewMgService.deleteReviewMg(meberDeleteArr[i]);
	       }
	       
	       return "redirect:/manage/memberMg/main";
	   }
	   
	

}
