package com.project.dwine.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.member.model.vo.UserImpl;
import com.project.dwine.mypage.model.service.MypageService;
import com.project.dwine.mypage.model.vo.Point;
import com.project.dwine.mypage.model.vo.Purchase;
import com.project.dwine.mypage.model.vo.Review;
import com.project.dwine.mypage.model.vo.Wish;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Transactional
@Controller
//@RequestMapping("/mypage")
public class MypageController {
	
	private MypageService mypageService;
	
	@Autowired
	public MypageController(MypageService mypageService) {
		this.mypageService = mypageService;
	}
	// 회원정보가져오기
	@GetMapping("/mypage/memberModify")
	public ModelAndView memberModify(ModelAndView mv) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    Member m = mypageService.selectMember(user_no);
	    mv.addObject("m", m);
	    mv.setViewName("mypage/memberModify");
	    return mv;
	    
	}
	
	//회원정보변경
	@PostMapping("/mypage/memberModify")
	//@RequestMapping(value="/mypage/memberModify", method=RequestMethod.POST)
	public String memberModify(Model model, @RequestParam Map<String, String> parameters){
		int user_no = Integer.parseInt(parameters.get("user_no"));
		String user_pw = parameters.get("user_pw");
		String user_nickname = parameters.get("user_nickname");
		String user_name = parameters.get("user_name");
		String user_phone = parameters.get("user_phone");
		
		int result = mypageService.memberModify(user_no, user_pw, user_nickname, user_name, user_phone);
		
		if(result > 0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
		
		return "redirect:/mypage/memberModify";
		
	}
	
	// 비밀번호 변경
	@GetMapping("/mypage/pwdModify")
	public void pwdModify(Model model) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    model.addAttribute("user_no", user_no);
	}
	
	@PostMapping("/mypage/pwdModify")
	public String pwdModifyForm(@RequestParam(required = false) String newPwd, @RequestParam(required = false) int user_no,
			@RequestParam(required = false) String user_pwd, HttpServletResponse response) throws IOException {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String pw = user.getUser_pw();
		System.out.println(pw);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String user_pw = passwordEncoder.encode(newPwd);
		boolean test = passwordEncoder.matches(user_pwd, pw);
		PrintWriter out = response.getWriter();
		if(test == true) {
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			int result1 = mypageService.pwdModfiytest(user_no, user_pw);
			// 등록을 마친 뒤 회원정보페이지를 응답하고자 할때
			// 새로 갱신 된 목록을 불러오는 요청을 해야하므로 다시 회원정보페이지 재호출
			out.println("<script language='javascript' charset='UTF-8'>alert('비밀번호가 변경되었습니다.'); location.href='/mypage/memberModify'; opener.window.location.reload(); self.close(); </script>");
			out.flush();
		} else {
			out.println("<script>alert('비밀번호가 변경에 실패하였습니다.'); location.href='/mypage/memberModify' </script>");
			System.out.println("no match");
			String str="";
			   str = "<script language='javascript'>";
			   str += "opener.window.location.reload();";  //오프너 새로고침
			   str += "self.close();";   // 창닫기
			    str += "</script>";
			   out.print(str);
		}
		
		return "/mypage/memberModify";
	}
	
	// 회원탈퇴 페이지 - 회원정보가져옴
	@GetMapping("/mypage/memberDel")
	public ModelAndView memberDelete(ModelAndView mv) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    Member m = mypageService.selectMember(user_no);
	    mv.addObject("m",m);
	    mv.setViewName("mypage/memberDel");
	    return mv;
	}
	
	// 비밀번호확인 후 탈퇴처리
	@PostMapping("/mypage/memberDel")
	public String memberDel(@RequestParam(required = false) int user_no ,@RequestParam(required = false) String user_pwd, HttpServletResponse response) throws IOException {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String pw = user.getUser_pw();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		boolean test = passwordEncoder.matches(user_pwd, pw);
		if(test == true) {
			int result = mypageService.deleteMember(user_no);
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('탈퇴에 성공하였습니다.'); location.href='/home';</script>");
			out.flush();
			SecurityContextHolder.clearContext();
			//return "redirect:/";
			return "/home/home";
		} else {
			System.out.println("no match");
			return "/home/home";
			
		}
	}
	
	@GetMapping("/mypage/orderlist")
	public ModelAndView orderList(ModelAndView mv) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    List<Purchase> purchaseList = mypageService.selectOrderList(user_no);
	    mv.addObject("purchaseList", purchaseList);
	    mv.setViewName("mypage/orderlist");
		return mv;
	}
	
	@PostMapping("/mypage/pickupModify")
	public String pickupModify(@RequestParam Map<String, String> parameters, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=euc-kr");
		int purchase_no = Integer.parseInt(parameters.get("purchase_no"));
		String pickup_place = parameters.get("pickup_place");
		String pickup_date = parameters.get("pickup_date");
		String pickup_time = parameters.get("pickup_time");
		int result = mypageService.pickupModify(purchase_no, pickup_place, pickup_date, pickup_time);
		if(result > 0) {
			System.out.println("성공");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('픽업정보가 변경되었습니다.'); location.href='/mypage/orderlist';</script>");
			out.flush();
			return "/mypage/orderlist";
		} else {
			System.out.println("실패");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('픽업정보 변경에 실패하였습니다.'); location.href='/mypage/orderlist';</script>");
			return "/mypage/orderlist";
		}
		
	}
	
//	@GetMapping("/review")
//	public void reviewList() {
//	}
	
	// 나의 리뷰 리스트
	@GetMapping("/mypage/review")
	public ModelAndView reviewList(ModelAndView mv) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
		List<Review> reviewList = mypageService.findAllReview(user_no);
		mv.addObject("reviewList", reviewList);
		mv.setViewName("mypage/review");
		return mv;
	}
	
//	@GetMapping("/mypage/wish")
//	public void wishList() {}
	
	// 찜 목록 보여주기
	@GetMapping("/mypage/wish")
	public ModelAndView wishListPage(ModelAndView mv) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    List<Wish> wishList = mypageService.selectWishList(user_no);
	    mv.addObject("wishList",wishList);
	    mv.setViewName("mypage/wish");
	    System.out.println("WISHLIST : " + wishList);
	    return mv;
	}
	
	// 찜 목록에서 삭제
	@GetMapping("/mypage/wish/{wish_no}")
	public String deleteOneWish(@PathVariable int wish_no, Model model, HttpServletResponse response) throws IOException {
		int result = mypageService.deleteOneWish(wish_no);
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>alert('찜 목록에서 삭제되었습니다.'); location.href='/mypage/wish' </script>");
			out.flush();
			//return "redirect:/mypage/wish";
			return "/mypage/wish";
		} else {
			out.println("<script>alert('삭제에 실패하였습니다.'); location.href='/mypage/wish' </script>");
			out.flush();
			//return "redirect:/mypage/wish";
			return "/mypage/wish";
		}
	}
	
	@GetMapping("/mypage/point")
	public ModelAndView pointListPage(ModelAndView mv) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    List<Point> pointList = mypageService.pointList(user_no);
	    List<Purchase> purchaseList = mypageService.purchaseList(user_no);
	    mv.addObject("purchaseList", purchaseList);
	    mv.addObject("pointList", pointList);
	    System.out.println(pointList);
	    mv.setViewName("mypage/point");
	    return mv;
	}
	
	@GetMapping("/mypage/reviewInsert")
	public String reviewInsertFormPage(int user_no, int od_no, int product_no, Model model) {
		model.addAttribute("user_no", user_no);
		model.addAttribute("od_no", od_no);
		model.addAttribute("product_no", product_no);
		return "/mypage/reviewInsert";
	}
	
	// 리뷰작성
	@PostMapping("/mypage/reviewInsert")
	public String reviewInserForm(@RequestParam Map<String, String> parameters) {
		int od_no = Integer.parseInt(parameters.get("od_no"));
		int user_no = Integer.parseInt(parameters.get("user_no"));
		String review_text = parameters.get("review_text");
		String review_image = parameters.get("review_image");
		double star = Double.parseDouble(parameters.get("star"));
		
		int result = mypageService.reviewInsert(review_text, review_image, star, user_no, od_no);
		if(result > 0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
		
		return "redirect:/mypage/review";
	}
	
	@GetMapping("/mypage/review/{review_no}")
	public String reviewUpdatePage(Model model, @PathVariable int review_no) {
	    
	    Review r = mypageService.selectOneReview(review_no);
	    model.addAttribute("review", r);
	    System.out.println(r);
	    
		return "mypage/reviewUpdate.html";
	}
	
	// 리뷰 삭제
	@GetMapping("/mypage/reviewDelete/{review_no}")
	public String reviewDelete(@PathVariable int review_no, RedirectAttributes rttr, Locale locale) {
		int result = mypageService.reviewDelete(review_no);
		if(result > 0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
		return "redirect:/mypage/review";
	}
	
	

}
