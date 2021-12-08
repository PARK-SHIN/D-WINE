package com.project.dwine.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.manage.model.service.ReviewMgService;
import com.project.dwine.manage.model.vo.Report;
import com.project.dwine.manage.model.vo.Review;
import com.project.dwine.mypage.model.service.MypageService;
import com.project.dwine.notice.model.vo.Notice;
import com.project.dwine.paging.PageInfo;

@Controller
@RequestMapping("/manage")
public class ReviewMgController {
	private ReviewMgService reviewMgService;
	private MypageService mypageService;
	
	@Autowired
	public ReviewMgController(ReviewMgService reviewMgService, MypageService mypageService) {
		this.reviewMgService = reviewMgService;
		this.mypageService = mypageService;
	}
	

	@GetMapping("/reviewMg/main")
	public ModelAndView reportMgList(ModelAndView mv, @RequestParam(value="page", required=false) String page) {
		
		int listCount = reviewMgService.reviewMgTotalListCnt();
		//System.out.println(listCount);
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 10);
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
		
		List<Review> reviewMgList = reviewMgService.selectReviewList(startRow, endRow);
		
		mv.addObject("reviewMgList", reviewMgList);
		mv.addObject("pi", pi);
		mv.setViewName("manage/reviewMg/main");
		return mv;
	}
	
	//인벤토리 검색
 	@PostMapping("/reviewMg/main")
 	@ResponseBody
 	public Map<String, Object> seachMainNotice(@RequestParam(value="page", required=false) String page) throws IOException {
 		
 		int listCount = reviewMgService.reviewMgTotalListCnt();
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 10);
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		List<Review> reviewMgList = reviewMgService.selectReviewList(startRow, endRow);
 		Map<String, Object> map = new HashMap<>();
 		map.put("pi", pi);
 		map.put("reviewMgList", reviewMgList);
 		
 		return map;
 	}
	
	
	//detail
	@GetMapping("/reviewMg/rdetail/{review_no}")
	public String reviewDetailPage(Model model, @PathVariable int review_no) {
	    
	    Review r = reviewMgService.selectOneReview(review_no);
	    model.addAttribute("review", r);
	    
		return "manage/reviewMg/rdetail.html";
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
