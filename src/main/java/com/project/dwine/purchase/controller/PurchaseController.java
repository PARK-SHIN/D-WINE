package com.project.dwine.purchase.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.member.model.vo.UserImpl;
import com.project.dwine.purchase.model.service.PurchaseService;
import com.project.dwine.purchase.model.vo.Product;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	private PurchaseService purchaseService;
	
	@Autowired
	public PurchaseController(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	/* 모든 와인 리스트 조회 */
//	@GetMapping("/wine_list")
//	public void getWineList(Model model) {
//		model.addAttribute("wineList", purchaseService.wineList());
//	}
//	

		
	
	/* 모든 와인 리스트 조회 */
	@GetMapping("wine_list")
	public ModelAndView getWineList(ModelAndView mv) {
		List<Product> wineList = purchaseService.wineList();
		mv.addObject("wineList", wineList);
		mv.setViewName("purchase/wine_list");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/filterWineList", method = { RequestMethod.POST })
	public List<Product> filterWineList(@RequestBody Map<String, String> param){
		String type = (String)param.get("type");
	/*	int price = 0;
		if(param.get("price") == "") {
			price = 0;
			System.out.println(price);
		} else {
			price = Integer.parseInt(param.get("price"));
		}
		
		*/
		String price = (String)param.get("price");
		String country = (String)param.get("country");
		String variety = (String)param.get("variety");
		String name = (String)param.get("name");
		
		System.out.println(country);
		
		System.out.println("TYPE : " + type + "PRICE : " +  price + "COUNTRY : " + country + "VARIETY : " + variety + "NAME : " + name);
		// List<Product> list = purchaseService.filterWineList(type, price, country, variety, name);
		
		// System.out.println("결과 : " + list);
		
		return purchaseService.filterWineList(type, price, country, variety, name);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/sortWineList", method = { RequestMethod.POST })
	public List<Product> sortWineList(@RequestBody String val){
		
		String value = val.replace("\"", "");
		System.out.println(value);
		
		List<Product> lis = purchaseService.sortWineList(value);
		System.out.println(lis);
		
		return lis;
	}
	
	/* 와인 디테일 페이지 */
	@GetMapping("{id}")
	public String room(@PathVariable String id, Model model) {
		
		Product product = purchaseService.wineDetail(id);
		System.out.println(product);
		
		model.addAttribute("product", product);

		return "purchase/wine_detail";
	}
	
	/* 리뷰 신고 */
	@GetMapping("/reviewReport")
	public void reviewReport(Model model) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    model.addAttribute("user_no", user_no);
	}
	
	
	
	
	
	
}





























