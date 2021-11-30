package com.project.dwine.purchase.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.member.model.vo.UserImpl;
import com.project.dwine.purchase.model.service.PurchaseService;
import com.project.dwine.purchase.model.vo.OrderDetail;
import com.project.dwine.purchase.model.vo.Payment;
import com.project.dwine.purchase.model.vo.Point;
import com.project.dwine.purchase.model.vo.Product;
import com.project.dwine.purchase.model.vo.Purchase;
import com.project.dwine.purchase.model.vo.Review;
import com.project.dwine.wish.model.vo.Wish;


@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	private PurchaseService purchaseService;
	
	@Autowired
	public PurchaseController(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	
	/* 모든 와인 리스트 조회 */
	@GetMapping("wine_list")
	public ModelAndView getWineList(ModelAndView mv) {
		List<Product> wineList = purchaseService.wineList();
		mv.addObject("wineList", wineList);
		mv.setViewName("purchase/wine_list");
		return mv;
	}
	
	/* 와인 검색 필터 */
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
	
	/* 와인 정렬 */
	@ResponseBody
	@RequestMapping(value = "/sortWineList", method = { RequestMethod.POST })
	public List<Product> sortWineList(@RequestBody String val){
		
		String value = val.replace("\"", "");
		System.out.println(value);
		
		List<Product> lis = new ArrayList<Product>();
		
		if(value.equals("popular")) {
			System.out.println("인기순");
			lis = purchaseService.popularList(value);
			System.out.println("인기순 : " + lis);
		} else {
			lis = purchaseService.sortWineList(value);
			System.out.println(lis);
			
		}
		
		
		return lis;
	}
	
	/* 와인 디테일 페이지 */
	@GetMapping("{id}")
	public String wineDetail(@PathVariable String id, Model model, @AuthenticationPrincipal User loginCheck) {
		
		Wish wish = null;
		if(loginCheck != null) {
			System.out.println("로그인한상태로 찜목록");
			UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int user_no = user.getUser_no();
			int product_no = Integer.parseInt(id);			

			wish = purchaseService.checkWish(user_no, product_no);
			System.out.println(wish);
		}
		
		/* 상품이 찜목록에 있는지 확인 */
		if (wish == null) {
			wish = new Wish();
		}
		
		Product product = purchaseService.wineDetail(id);
		List<Review> review = purchaseService.reviewList(id);
		
		// 상품에 review가 없으면 null로 바꿔주고 보내준다.
		System.out.println(review.size());
		
	/*
		if(review.get(0) == null) {
			System.out.println("List is empty2");
			review = null;
			System.out.println("review : " + review);
			
		} 
		*/
		model.addAttribute("review", review);
		model.addAttribute("wish", wish);
		model.addAttribute("product", product);
		
	
		


		System.out.println(wish);
		System.out.println(product);
		System.out.println("review : " + review);
	
		return "purchase/wine_detail";
	}
	
	
	
	
	
	
	
	/* 약관 보여주기 */
	@GetMapping("/clause")
	public void clause(Model model) {
		
	}
	
	/* purchase Table 값 넣기  */
	@ResponseBody
	@RequestMapping(value = "/insert", method = { RequestMethod.POST })
	public String purchaseInsert(@RequestParam() int use_point,
			@RequestParam() int purchase_price,
			@RequestParam() String pickup_date,
			@RequestParam() String pickup_place,
			@RequestParam() String pickup_time,
			@RequestParam() int havePoint,  
			@RequestParam() String purchase_no){
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    
	    Purchase purchase = new Purchase();
	    
		purchase.setUser_no(user_no);
		purchase.setUse_point(use_point);
		purchase.setPurchase_price(purchase_price);
		purchase.setPickup_date(pickup_date);
		purchase.setPickup_place(pickup_place);
		purchase.setPickup_time(pickup_time);
		purchase.setPurchase_no(purchase_no);

		System.out.println("purchase 확인 : " +  purchase);
	
		
		 int result1 = purchaseService.purchaseInsert(purchase);
		
		 if(result1 > 0) {
			
			int usePoint = purchase.getUse_point();
			int point = (Math.round((int)(purchase.getPurchase_price() * 0.05)));
			
			System.out.println("가진 포인트 : " + havePoint);
			System.out.println("사용한 포인트 : " + usePoint);
			System.out.println("적립 포인트 : " + point);
			System.out.println(result1);
			System.out.println(user_no);
			
			// 회원의 구매전 적립금 - 사용포인트 + 구매 적립 포인트
			int user_point = havePoint - usePoint + point;
			System.out.println("총 적립금 : " + user_point);
			
			/* 회원 테이블 포인트 Update */
			int result2 = purchaseService.memberPoint(user_no, user_point);
			System.out.println(result2);
			
			/* 포인트 테이블 Insert */
			int result3 = purchaseService.pointPlus(user_no, point, purchase_no);	
			System.out.println(result3);

		 }
		
		 return "redirect:/purchase/purchase_detail";					
		
	}
	

	/* payment Table 값 넣기  */
	@ResponseBody
	@RequestMapping(value = "/paymentInsert", method = { RequestMethod.POST })
	public String paymentInsert(
			@RequestParam() String pay_no,
			@RequestParam() String pay_method,
			@RequestParam() String purchase_no){
		
	    Payment payment = new Payment();
	    payment.setPay_no(pay_no);
	    payment.setPay_method(pay_method);
	    payment.setPurchase_no(purchase_no);



		System.out.println("payment 확인 : " +  payment);

		int result1 = purchaseService.paymentInsert(payment);

		return "redirect:/purchase/purchase_detail";					
		
	}
	
	
	/* orderDetail Table 값 넣기  */
	@ResponseBody
	@RequestMapping(value = "/orderDetail", method = { RequestMethod.POST })
	public String orderDetailInsert(
			@RequestParam() int od_count,
			@RequestParam() int od_price,
			@RequestParam() String purchase_no,
			@RequestParam() int product_no){
		
	    OrderDetail orderDetail = new OrderDetail();
	    orderDetail.setOd_count(od_count);
	    orderDetail.setOd_price(od_price);
	    orderDetail.setPurchase_no(purchase_no);
	    orderDetail.setProduct_no(product_no);


		System.out.println("purchase 확인 : " +  orderDetail);

		int result1 = purchaseService.orderDetailInsert(orderDetail);

		return "redirect:/purchase/purchase_detail";					
		
	}
	
	
	/* 상품 재고 업데이트 */
	@ResponseBody
	@RequestMapping(value = "/stockUpdate", method = { RequestMethod.POST })
	public String stockUpdate(
			@RequestParam(value="productNo[]") List<Integer> productNo, 
			@RequestParam(value="stockArr[]") List<Integer> stockArr)  {
		System.out.println("도달");
		System.out.println(productNo);
		System.out.println(stockArr);
		
		int size = productNo.size();
		
		System.out.println(size);
		
		int product_no = 0;
		int product_count = 0;
		int result = 0;
		/*
		 * product_no = productNo.get(1); 
		 * product_count = stockArr.get(1);
		 * System.out.println("상품번호 : " + product_no); 
		 * System.out.println("재고 : " + product_count);
		 */
	 
		for(int i=0; i<size; i++) {
			System.out.println("반복분");
			product_no = productNo.get(i);
			product_count = stockArr.get(i);
			System.out.println(product_no);
			System.out.println(product_count);
			
			result = purchaseService.stockUpdate(product_no, product_count);

		}
		//result = purchaseService.stockUpdates(productNo, stockArr);

	
		// System.out.println(cart_no.get(1));
		// System.out.println(cart_no.get(1).getClass().getName());
		/*
		 * UserImpl user =
		 * (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal
		 * (); int user_no = user.getUser_no();
		 */
	/*
		int size = cart_no.size();
		
		int result = 0;
	
			
			result = purchaseService.cartDelete(user_no, cart_no);	
	*/	
		

		
		return "redirect:/purchase/purchase_detail";
	}
	
	
	/* 장바구니 삭제 */
	@ResponseBody
	@RequestMapping(value = "/cartDelete", method = { RequestMethod.POST })
	public String cartDelete(@RequestParam(value="valueArr[]") List<Integer> cart_no)  {
		System.out.println("도달");
		System.out.println(cart_no);
	
		System.out.println(cart_no.size());
		// System.out.println(cart_no.get(1));
		// System.out.println(cart_no.get(1).getClass().getName());

		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    
		int size = cart_no.size();
		
		int result = 0;
	
			
			result = purchaseService.cartDelete(user_no, cart_no);	
		
		

		System.out.println("삭제 되었는지 : " + result);
		return "redirect:/purchase/purchase_detail";
	}
	
	
	/* 구매완료 페이지 이동 */
	@GetMapping("complete/{purchase_no}")
	public ModelAndView purchaseComplete(ModelAndView mv, @PathVariable String purchase_no) {
		//List<Product> wineList = purchaseService.wineList();
		//mv.addObject("wineList", wineList);
		System.out.println(purchase_no);
		
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_no = user.getUser_no();
		
		Member member = purchaseService.memberinfo(user_no);
		
		System.out.println("member : " + member);
		
		Point point = purchaseService.selectPoint(purchase_no);
		
		System.out.println("point : " + point);
		
		
		
	  Purchase purchase =purchaseService.selectPurchase(purchase_no);
	  
	  System.out.println("purchase : " + purchase);
	 
	
	  List<OrderDetail> orderDetail = purchaseService.selectOrderDetail(purchase_no);
	  
	  System.out.println("orderDetail : " + orderDetail);
		 

		mv.addObject("member", member);
		mv.addObject("orderDetail", orderDetail);
		mv.addObject("point", point);
		mv.addObject("purchase", purchase);
		mv.setViewName("purchase/purchase_complete");
		return mv;
	}
	
	
	/* 리뷰 신고 */
	/*
	@GetMapping("/reviewReport")
	public String reviewReport(Model model,String shin) {
	    
	    model.addAttribute("shin", shin);
	    
		System.out.println("리뷰 번호 : " + shin);
		return "purchase/reviewReport.html";
	}
	*/
	
	@RequestMapping("/reviewReport")
	public void reviewReport(Model model, @RequestParam(required = false) String shin){
		
		System.out.println("shin :" + shin);
		model.addAttribute("shin", shin);
	}



	/*
	 * @GetMapping("/reviewReport/{userNo}") public String reviewReport(Model
	 * model, @PathVariable int userNo) {
	 * 
	 * System.out.println("유저번호 : " + userNo); model.addAttribute("userNo", userNo);
	 * 
	 * return "purchase/reviewReport.html";
	 * 
	 * 
	 * }
	 */
	
	/*
	 * @GetMapping("/reviewReport") public String reviewReport() {
	 * 
	 * 
	 * 
	 * return "purchase/reviewReport.html";
	 * 
	 * 
	 * }
	 */
	
	
	
	
	
}





























