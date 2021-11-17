package com.project.dwine.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.product.model.service.ProductService;
import com.project.dwine.product.model.vo.Country;
import com.project.dwine.product.model.vo.Product;
import com.project.dwine.product.model.vo.Type;
import com.project.dwine.product.model.vo.Variety;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("list")
	public ModelAndView productList(ModelAndView mv) {
		
		List<Product> productList = productService.selectProductList();
		
		mv.addObject("productList", productList);
		mv.setViewName("product/list");
		
		return mv;
	}
	
	@GetMapping(value = "category", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> findCategoryList() {
		Map<String, Object> map = new HashMap<>();
		List<Type> typeList = productService.selectTypeList();
		List<Country> countryList = productService.selectCountryList();
		List<Variety> varietyList = productService.selectVarietyList();
		
		map.put("type", typeList);
		map.put("country", countryList);
		map.put("variety", varietyList);
		
		return map;
	}
	
	@GetMapping("{productNo}")
	public String selectProductByNo(@PathVariable int productNo, Model model) {
		Product product = productService.selectProductByNo(productNo);
		model.addAttribute("product", product);
		
		return "product/detailView";
	}
	
	@GetMapping("regist") 
	public void registPage() {}
	
	@PostMapping("regist")
	public String registProduct(Model model, @RequestParam MultipartFile thumbnail, @RequestParam MultipartFile labelImg, HttpServletRequest request) {
		// 맛그래프, 이미지 제외 정보들
		String kname = request.getParameter("kname");
		String ename = request.getParameter("ename");
		int costPrice = Integer.parseInt(request.getParameter("costPrice"));
		int salePrice = Integer.parseInt(request.getParameter("salePrice"));
		String productCount = request.getParameter("productCount");
		String winary = request.getParameter("winary");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		int abv = Integer.parseInt(request.getParameter("abv"));
		String sweetness = request.getParameter("sweetness");
		String acidity = request.getParameter("acidity");
		String body = request.getParameter("body");
		String tannin = request.getParameter("tannin");
		String information = request.getParameter("information");
		String award = request.getParameter("award");
		String tip = request.getParameter("tip");
		
		// 맛 그래프
		String mood1 = request.getParameter("mood1");
		String mood2 = request.getParameter("mood2");
		String food1 = request.getParameter("food1");
		String food2 = request.getParameter("food2");
		String tasteGraph = mood1 + "/" + mood2 + "/" + food1 + "/" + food2;
		
		// 이미지(썸네일, 라벨이미지)
		

		Product product = new Product();
	
		/*
		int result = productService.registProduct(product);
		int result2 = 0;
		
		if(result > 0) {
			int productNo = productService.selectCurrProductNo();
			
			if(productNo > 0) {
				for(int i = 0; i < 4; i++) {
					result2 += productService.registProductHash(productNo);
					
					if(result2 == 4) {
						return null;
					}
				}
			}
		}
		*/
		return null;/*"redirect:/product/detailView/" + product.getId();*/
	}
	
}
