package com.project.dwine.manage.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.hashtag.model.vo.Hashtag;
import com.project.dwine.manage.model.service.InventoryMgService;
import com.project.dwine.manage.model.vo.Inventory;
import com.project.dwine.product.model.service.ProductService;
import com.project.dwine.product.model.vo.Product;



@Controller
@RequestMapping("/manage")
public class InventoryMgController {
	private InventoryMgService inventoryMgService;
	private ProductService productService;
	
	@Autowired
	public InventoryMgController(InventoryMgService inventoryMgService, ProductService productService ) {
		this.inventoryMgService = inventoryMgService;
		this.productService = productService;
	}
	
	//메인화면
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
	//입고등록
	@GetMapping("/inventoryMg/regist") 
	public ModelAndView registPage(ModelAndView mv) {
		
		Inventory totalStock = inventoryMgService.selectTotalStock();
		Inventory totalShop = inventoryMgService.selectTotalShop();
		Inventory todayReceiving = inventoryMgService.selectTodayReceiving();
		
		mv.addObject("totalStock", totalStock);
		mv.addObject("totalShop", totalShop);
		mv.addObject("todayReceiving", todayReceiving);
		
		mv.setViewName("manage/inventoryMg/regist");
		return mv;
	}
	
	//입고를 공지사항과 다르게 적용해보기
	
	@PostMapping("/inventoryMg/regist") //동일url이지만 전송버튼을 눌렀을때 하는 것. 
	public String registInven(Model model, HttpServletRequest request) {
	
	    int inven_count = Integer.parseInt(request.getParameter("inven_count"));
		int inven_cost = Integer.parseInt(request.getParameter("inven_cost"));
		int product_no = Integer.parseInt(request.getParameter("product_no"));
		
		Inventory inven = new Inventory(inven_count, inven_cost, product_no);
		
		int result = inventoryMgService.insertInventory(inven);
		
		return "redirect:mamage/inventoryMg/regist";
	}
	
	
	
	//상품검색
	@PostMapping("/inventoryMg/search")
	@ResponseBody
	public List<Product> seachProduct(@RequestParam String searchStandard, @RequestParam String searchValue) throws IOException {
		
		List<Product> searchList = productService.searchProductList(searchStandard, searchValue);
		
		return searchList;
	}
	
	//상품no로 regist에 불러오기
	@PostMapping("/inventoryMg/searchByProductNo")
	@ResponseBody
	public Product selectProductByNo(@RequestParam int productNo) {
				
		return productService.selectProductByNo(productNo);
	}
	
	
	
	
	

}
