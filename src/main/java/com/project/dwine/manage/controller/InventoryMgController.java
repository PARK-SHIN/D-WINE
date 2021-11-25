package com.project.dwine.manage.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.manage.model.service.InventoryMgService;
import com.project.dwine.manage.model.vo.Inventory;



@Controller
@RequestMapping("/manage")
public class InventoryMgController {
	private InventoryMgService inventoryMgService;
	
	@Autowired
	public InventoryMgController(InventoryMgService inventoryMgService) {
		this.inventoryMgService = inventoryMgService;
	}
	
	
	
	@GetMapping("/inventoryMg/main")
	public ModelAndView invenList(ModelAndView mv) {
		List<Inventory> invenList = inventoryMgService.selectInvenMgList();
		
		Inventory totalStock = inventoryMgService.selectTotalStock();
		Inventory totalShop = inventoryMgService.selectTotalShop();
		Inventory todayReceiving = inventoryMgService.selectTodayReceiving();
		mv.addObject("invenList", invenList);
		mv.addObject("totalStock", totalStock);
		mv.addObject("totalShop", totalShop);
		mv.addObject("todayReceiving", todayReceiving);
		
		mv.setViewName("manage/inventoryMg/main");
		return mv;
	}
	
	

}
