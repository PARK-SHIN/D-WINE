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
		List<Total> years = salesInquiryService.selectYear();
		List<Total> yearSales = salesInquiryService.yearSales();
		List<Total> salesProduct = salesInquiryService.salesProduct();

		mv.addObject("total", total);
		mv.addObject("years", years);
		mv.addObject("yearSales", yearSales);
		mv.addObject("salesProduct", salesProduct);
		mv.setViewName("salesInquiry/list");
		
		return mv;
	}
	
	@PostMapping("selectChangeYear")
	@ResponseBody
	public List<Total> selectChangeYear(@RequestParam String year) {
		List<Total> changeYearSales = salesInquiryService.changeYearSales(year);
		for(Total t : changeYearSales) {
			System.out.println(t);
		}
		
		return changeYearSales;
	}
	
	@GetMapping("daily")
	public Model dailyList(Model model, @RequestParam(value="page", required=false) String page,
										@RequestParam(value="startDate", required=false) String startDate, 
										@RequestParam(value="endDate", required=false) String endDate) {
		
		Map<String, Object> daily = new HashMap<>();
		daily.put("startDate", startDate);
		daily.put("endDate", endDate);
		
		int dailyCount = salesInquiryService.getDailyCount(daily);
		
		int resultPage = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		PageInfo pi = new PageInfo(resultPage, dailyCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
		
        daily.put("startRow", startRow);
        daily.put("endRow", endRow);
        
		List<Total> dailyList = salesInquiryService.searchDailyList(daily);
		
		model.addAttribute("pi", pi);
		model.addAttribute("dailyList", dailyList);
		
		return model;
	}
	
	@PostMapping("daily")
	@ResponseBody
	public Map<String, Object> searchDateList(Model model, @RequestParam(value="page", required=false) String page,
														   @RequestParam(value="startDate", required=false) String startDate, 
														   @RequestParam(value="endDate", required=false) String endDate) {
					
		Map<String, Object> daily = new HashMap<>();
		daily.put("startDate", startDate);
		daily.put("endDate", endDate);
		
		int dailyCount = salesInquiryService.getDailyCount(daily);
		
		int resultPage = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		PageInfo pi = new PageInfo(resultPage, dailyCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
        
        daily.put("startRow", startRow);
        daily.put("endRow", endRow);
        
		List<Total> dailyList = salesInquiryService.searchDailyList(daily);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pi", pi);
		map.put("dailyList", dailyList);
		
		return map;
	}
	
	
}
