package com.project.dwine.wish.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.project.dwine.member.model.vo.UserImpl;
import com.project.dwine.purchase.model.vo.Product;
import com.project.dwine.wish.model.service.WishService;
import com.project.dwine.wish.model.vo.Wish;

@Controller
@RequestMapping("/wish")
public class WishController {
	
	private WishService wishService;
	
	@Autowired
	public WishController(WishService wishService) {
		this.wishService = wishService;
	}

	/*
	 * @GetMapping("list") public ModelAndView getCartList(ModelAndView
	 * mv, @AuthenticationPrincipal User user){
	 * 
	 * mv.setViewName("home/home"); return mv; }
	 */
	
	/* 상품 찜 추가 */
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Map<String, String> addWish(@RequestBody int product_no , @AuthenticationPrincipal User users){

		Map<String, String> map = new HashMap<>();

		if(users == null) {
			String msg = "로그인이 필요합니다.";
			map.put("msg", msg);
		} else {
			
			UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int user_no = user.getUser_no();

			String msg = wishService.addWish(user_no, product_no) > 0 ? "찜 등록되었습니다." : "찜 등록에 실패하였습니다.";
			map.put("msg", msg);
			}
		return map;
	}
	
	/* 상품 찜 삭제 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public Map<String, String> deleteWish(@RequestBody int product_no , @AuthenticationPrincipal User users){

		Map<String, String> map = new HashMap<>();

		if(users == null) {
			String msg = "로그인이 필요합니다.";
			map.put("msg", msg);
		} else {
			
			UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int user_no = user.getUser_no();

			String msg = wishService.deleteWish(user_no, product_no) > 0 ? "찜 삭제되었습니다." : "찜 삭제에 실패하였습니다.";
			map.put("msg", msg);
			}
		return map;
	}

}
