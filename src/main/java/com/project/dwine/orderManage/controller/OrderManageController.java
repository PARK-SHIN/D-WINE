package com.project.dwine.orderManage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.orderManage.model.service.OrderManageService;
import com.project.dwine.orderManage.model.vo.Purchase;
import com.project.dwine.paging.PageInfo;
import com.project.dwine.salesInquiry.model.vo.Total;

@Controller
@RequestMapping("/orderManage")
public class OrderManageController {

	private OrderManageService orderManageService;
	
	@Autowired
	public OrderManageController(OrderManageService orderManageService) {
		this.orderManageService = orderManageService;
	}
	
	// 주문리스트 조회
	@GetMapping("list")
	public ModelAndView orderList(ModelAndView mv, @RequestParam(value="page", required=false) String page,
			 @RequestParam(value="startDate", required=false) String startDate, @RequestParam(value="endDate", required=false) String endDate,
			 @RequestParam(value="searchCondition", required=false) String searchCondition, @RequestParam(value="searchValue", required=false) String searchValue,
			 @RequestParam(value="searchStatus", required=false) String searchStatus) {
		
		Map<String, Object> order = new HashMap<String, Object>();

		order.put("startDate", startDate);
		order.put("endDate", endDate);
		order.put("searchCondition", searchCondition);
		order.put("searchValue", searchValue);
		order.put("searchStatus", searchStatus);
		
		int originCount = orderManageService.getSearchListCount(order);
		
		int resultPage = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		PageInfo pi = new PageInfo(resultPage, originCount, 10, 10);
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
        order.put("startRow", startRow);
        order.put("endRow", endRow);
        
		List<Purchase> orderList = orderManageService.searchOrderList(order);
		
		mv.addObject("originCount", originCount);
		mv.addObject("pi", pi);
		mv.addObject("orderList", orderList);
		mv.setViewName("orderManage/list");
		
		return mv;
	}
	
	@PostMapping("list")
	@ResponseBody
	public Map<String, Object> searchDateList(Model model, @RequestParam(value="page", required=false) String page,
			 @RequestParam(value="startDate", required=false) String startDate, @RequestParam(value="endDate", required=false) String endDate,
			 @RequestParam(value="searchCondition", required=false) String searchCondition, @RequestParam(value="searchValue", required=false) String searchValue,
			 @RequestParam(value="searchStatus", required=false) String searchStatus) {
	
		Map<String, Object> order = new HashMap<String, Object>();

		order.put("startDate", startDate);
		order.put("endDate", endDate);
		order.put("searchCondition", searchCondition);
		order.put("searchValue", searchValue);
		order.put("searchStatus", searchStatus);
		
		int searchCount = orderManageService.getSearchListCount(order);
		
		int resultPage = 1;
		if(page != null) {
		resultPage = Integer.parseInt(page);
		}
		PageInfo pi = new PageInfo(resultPage, searchCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		order.put("startRow", startRow);
        order.put("endRow", endRow);
        
		List<Purchase> orderList = orderManageService.searchOrderList(order);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCount", searchCount);
		map.put("pi", pi);
		map.put("orderList", orderList);
		return map;
	}
	
	// 주문 상태 변경
	@PostMapping(value = "update")
	@ResponseBody
	public String updateOrderStatus(@RequestParam String purchaseNo, @RequestParam String odStatus, 
								  	@RequestParam int userNo, @RequestParam int usePoint) {
		
		
		int result1 = orderManageService.updateOrderStatus(purchaseNo, odStatus);	
		
		String str = "";
		if(odStatus.equals("환불완료") && odStatus.equals("결제취소")) {
			// point, member 테이블
			int result2 = orderManageService.updatePoint(purchaseNo);
			int result3 = orderManageService.updateMember(userNo, usePoint);
			
			if(result1 + result2 + result3 == 3) {
				str = "success";
			} else {
				str = "fail";
			} 
		} else {
			if(result1 > 0) {
				str = "success";
			} else {
				str = "fail";
			}
		}
		return str; 
	}
	
	// 일괄 상태 변경
	@PostMapping(value = "allChange")
	@ResponseBody
	public void updateAllChange(HttpServletResponse response, @RequestParam(value = "pNoArr[]") List<String> pNoArr, @RequestParam String odStatus) throws IOException {
		
		int results = 0;
		for(String purchaseNo : pNoArr) {
			results += orderManageService.updateAllChange(purchaseNo, odStatus);
		}
		
		if(pNoArr.size() == results) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
		}
	}
	
	// 주문 선택 삭제
	@PostMapping(value = "delete")
	@ResponseBody
	public void deleteOrder(HttpServletResponse response, @RequestParam(value = "checkArr[]") List<String> checkArr) throws IOException {
		
		int results = 0;
		for(String purchseNo : checkArr) {
			results += orderManageService.deleteOrder(purchseNo);
		}
		
		if(checkArr.size() == results) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
		}
	}
	
	// 주문 상세 보기
	@PostMapping(value = "detail")
	@ResponseBody
	public Purchase selectOrderDetail(@RequestParam String purchaseNo) {
			
		Purchase detail = orderManageService.selectOrderDetail(purchaseNo);
		
		return detail;
	}
	
}
