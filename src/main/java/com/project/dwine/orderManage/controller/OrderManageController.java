package com.project.dwine.orderManage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public void updateOrderStatus(@RequestParam int purchaseNo, @RequestParam String odStatus) {
		
		int result = orderManageService.updateOrderStatus(purchaseNo, odStatus);
	}
	
	// 주문 선택 삭제
	@PostMapping(value = "delete")
	@ResponseBody
	public void deleteOrder(HttpServletResponse response, @RequestParam(value = "checkArr[]") List<String> checkArr) throws IOException {
		
		List<Integer> arr = new ArrayList<>();
		
		for(String s : checkArr) {
			arr.add(Integer.parseInt(s));
		}
		
		int results = 0;
		for(int purchseNo : arr) {
			results += orderManageService.deleteOrder(purchseNo);
		}
		
		if(arr.size() == results) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
		}
	}
	
	// 주문 상세 보기
	// @PostMapping("detail")
	
}
