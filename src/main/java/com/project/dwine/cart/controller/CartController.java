package com.project.dwine.cart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.cart.model.service.CartService;
import com.project.dwine.cart.model.vo.Cart;
import com.project.dwine.member.model.vo.UserImpl;
import com.project.dwine.purchase.model.vo.Product;



@Controller
@RequestMapping("/cart")
public class CartController {

	private CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping("list")
	public ModelAndView getCartList(ModelAndView mv, @AuthenticationPrincipal User user){
		
		System.out.println(user);
	
		if(user == null) {
			mv.setViewName("/member/login");
			System.out.println("비회원입니다.");
		}else {
			UserImpl User = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int user_no = User.getUser_no();
			
			// List<Cart> cartList = cartService.cartList(user_no);
			
			List<Product> cartList = cartService.cartList(user_no);
			
			mv.addObject("cartList", cartList);
			
			System.out.println("장바구니 확인 : " + cartList);
			
			mv.setViewName("cart/cart_list");
		}
		
		
		return mv;
	}
	
	
	
	// cart에 상품 추가
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Map<String, String> addCart(@RequestBody Cart cart, @AuthenticationPrincipal User users){

		// String loginId = (String) httpSession.getAttribute("loginId");
		System.out.println("로그인 확인 2 :" + users);
		// System.out.println("로그인 확인" + loginId);
		
		Map<String, String> map = new HashMap<>();
	
		if(users == null) {
			String msg = "로그인이 필요합니다.";
			map.put("msg", msg);
			System.out.println("비로그인입니다.");
		} else {
			UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			cart.setUser_no(user.getUser_no());
			
			System.out.println("car확인 : " + cart);
			
			/*
			 * int user_no = cart.getUser_no(); 
			 * int product_no = cart.getProduct_no();
			 * 
			 * Cart checkCart = cartService.checkCart(user_no, product_no);
			 */
			
			Cart checkCart = cartService.checkCart(cart);
			
			System.out.println("chekc확인 : " + checkCart);

			if(checkCart == null){
				String msg = cartService.addCart(cart) > 0 ? "장바구니에 등록되었습니다." : "장바구니 등록에 실패하였습니다.";
				map.put("msg", msg);				
			} else {
				map.put("msg", "장바구니에 이미 있는 상품입니다.");
			}
	
		}

		return map;
	}
	
	
	
}
