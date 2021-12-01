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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.orderManage.model.service.OrderManageService;
import com.project.dwine.orderManage.model.vo.Purchase;

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
	public ModelAndView orderList(ModelAndView mv) {
		
		List<Purchase> orderList = orderManageService.selectOrderList();
		
		mv.addObject("orderList", orderList);
		mv.setViewName("orderManage/list");
		
		return mv;
	}
	
	// 총 주문건 수 불러오기
	@GetMapping(value = "totalCnt")
	@ResponseBody
	public Map<String, Object> totalCnt(){
		Map<String, Object> map = new HashMap<>();
		int totalCnt = orderManageService.selectOrderList().size();
		
		map.put("totalCnt", totalCnt);
		
		return map;
	}
	
	// 주문 상태 선택하여 조회
	@PostMapping(value = "state")
	@ResponseBody
	public List<Purchase> stateChangeList(@RequestParam("state") String state){
				
		List<Purchase> stateChangeList = orderManageService.stateChangeList(state);
		
		return stateChangeList;
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
	
	// 검색
	@PostMapping(value = "search")
	@ResponseBody
	public List<Purchase> selectSearchList(@RequestParam String searchStatus, 
										@RequestParam String searchCondition, @RequestParam String searchValue){
		
		List<Purchase> searchList = orderManageService.selectSearchList(searchStatus, searchCondition, searchValue);
		
		return searchList;
	}
	
}
