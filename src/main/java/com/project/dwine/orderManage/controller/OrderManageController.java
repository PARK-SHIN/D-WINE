package com.project.dwine.orderManage.controller;

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
	
	@GetMapping("list")
	public ModelAndView orderList(ModelAndView mv) {
		
		List<Purchase> orderList = orderManageService.selectOrderList();
		
		mv.addObject("orderList", orderList);
		mv.setViewName("orderManage/list");
		
		return mv;
	}
	
	@GetMapping(value = "totalCnt", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> totalCnt(){
		Map<String, Object> map = new HashMap<>();
		int totalCnt = orderManageService.selectOrderList().size();
		
		map.put("totalCnt", totalCnt);
		
		return map;
	}
	
	@PostMapping(value = "state", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Purchase> stateChangeList(@RequestParam("state") String state){
		
		System.out.println(state);
		
		List<Purchase> stateChangeList = orderManageService.stateChangeList(state);
		
		for(Purchase p : stateChangeList) {
			System.out.println(p);
		}
		
		return stateChangeList;
	}
	
	@PostMapping(value = "update", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public void updateOrderStatus(@RequestParam int purchaseNo, @RequestParam String odStatus, RedirectAttributes rttr) {
		
		System.out.println(purchaseNo);
		System.out.println(odStatus);
		
		int result = orderManageService.updateOrderStatus(purchaseNo, odStatus);
	}

}
