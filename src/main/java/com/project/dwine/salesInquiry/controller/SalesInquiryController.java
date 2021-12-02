package com.project.dwine.salesInquiry.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.paging.PageInfo;
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
		List<Total> yearSales = salesInquiryService.yearSales();

		mv.addObject("total", total);
		mv.addObject("yearSales", yearSales);
		mv.setViewName("salesInquiry/list");
		
		return mv;
	}
	
	@GetMapping("daily")
	public Model dailyList(Model model, @RequestParam(value="page", required=false) String page) {
		
		int dailyListCount = salesInquiryService.getDailyListCount();
		
		int resultPage = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		PageInfo pi = new PageInfo(resultPage, dailyListCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
		
		List<Total> dailyList = salesInquiryService.dailyList(startRow, endRow);
		
		model.addAttribute("pi", pi);
		model.addAttribute("dailyList", dailyList);
		
		return model;
	}
	
	@PostMapping("daily")
	@ResponseBody
	public Map<String, Object> searchDateList(Model model, @RequestParam(value="page", required=false) String page,
											 @RequestParam(value="startDate", required=false) String startDate, @RequestParam(value="endDate", required=false) String endDate) {
		
		int dateListCount = salesInquiryService.getDateListCount(startDate, endDate);
		int resultPage = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		PageInfo pi = new PageInfo(resultPage, dateListCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
		
		List<Total> dailyList = salesInquiryService.searchDateList(startRow, endRow, startDate, endDate);
		
		System.out.println(pi);
		System.out.println(resultPage);
		System.out.println(dateListCount);
		System.out.println(startDate);
		System.out.println(endDate);
		/*
		 * for(Total t : dailyList) { System.out.println(t); }
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pi", pi);
		map.put("dailyList", dailyList);
		
		return map;
	}

	
	
	
	
	
	
	
	
	
	
	
}
