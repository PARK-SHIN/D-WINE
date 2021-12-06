package com.project.dwine.mypage.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.member.model.vo.UserImpl;
import com.project.dwine.mypage.model.service.MypageService;
import com.project.dwine.mypage.model.vo.Point;
import com.project.dwine.mypage.model.vo.Purchase;
import com.project.dwine.mypage.model.vo.Review;
import com.project.dwine.mypage.model.vo.Wish;
import com.project.dwine.paging.PageInfo;

@Transactional
@Controller
public class MypageController{
	
	private MypageService mypageService;
	
	@Autowired
	public MypageController(MypageService mypageService) {
		this.mypageService = mypageService;
	}
	// 회원정보관련 --------------------------
	// 회원정보가져오기
	@GetMapping("/mypage/memberModify")
	public ModelAndView memberModify(ModelAndView mv) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    Member m = mypageService.selectMember(user_no);
	    System.out.println("회원정보수정페이지 : " + m);
	    mv.addObject("m", m);
	    mv.setViewName("mypage/memberModify");
	    return mv;
	    
	}
	
	//회원정보변경
	@PostMapping("/mypage/memberModify")
	//@RequestMapping(value="/mypage/memberModify", method=RequestMethod.POST)
	public String memberModify(Model model, @RequestParam Map<String, String> parameters, RedirectAttributes rttr) throws IOException{
		int user_no = Integer.parseInt(parameters.get("user_no"));
		String user_pw = parameters.get("user_pw");
		String user_nickname = parameters.get("user_nickname");
		String user_name = parameters.get("user_name");
		String user_phone = parameters.get("user_phone");
		
		int result = mypageService.memberModify(user_no, user_pw, user_nickname, user_name, user_phone);
		if(result > 0) {
			System.out.println("성공");
			rttr.addFlashAttribute("message", "회원정보가 변경되었습니다.");
		} else {
			System.out.println("실패");
			rttr.addFlashAttribute("message", "회원정보 변경에 실패하였습니다.");
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
	
	@PostMapping("/mypage/pwdModifyForm")
	public String pwdModifyForm(@RequestParam(required = false) String newPwd, @RequestParam(required = false) int user_no,
			@RequestParam(required = false) String user_pwd, HttpServletResponse response, RedirectAttributes rttr) throws IOException {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String pw = user.getUser_pw();
		System.out.println(pw);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String user_pw = passwordEncoder.encode(newPwd);
		boolean test = passwordEncoder.matches(user_pwd, pw);
		if(test == true) {
			int result1 = mypageService.pwdModfiytest(user_no, user_pw);
			System.out.println("비밀번호변경성공");
			rttr.addFlashAttribute("message", "비밀번호가 변경되었습니다.");
			return "redirect:/mypage/memberModify";
		} else {
			System.out.println("no match");
			rttr.addFlashAttribute("message", "비밀번호 변경에 실패하였습니다.");
			System.out.println("비밀번호변경실패");
			return "redirect:/mypage/memberModify";
		}
	}
	
	// 닉네임 변경
	@RequestMapping(value="/mypage/nicknameCheck" , method = {RequestMethod.GET, RequestMethod.POST})
	public void nicknameChenk(HttpServletResponse response, @RequestParam String user_nickname) throws IOException {
		
		int result = mypageService.nicknameCheck(user_nickname);

		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
	}
	
	// 회원탈퇴 페이지 - 회원정보가져옴
	@GetMapping("/mypage/memberDel")
	public ModelAndView memberDelete(ModelAndView mv) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    Member m = mypageService.selectMember(user_no);
	    System.out.println("회원탈퇴페이지 비밀번호가져오기 : " + m.getUser_pw());
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
		Member m = mypageService.selectMember(user_no);
		String aa = m.getUser_pw();
		
		boolean test = passwordEncoder.matches(user_pwd, aa);
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
	//----------------------------------------------
	// 주문목록
	@GetMapping("/mypage/orderlist")
	public ModelAndView orderList(ModelAndView mv,@RequestParam(value="page", required=false) String page) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    
	    int listCount = mypageService.getTotalListCount(user_no);
	    System.out.println("주문목록 listCount : " + listCount);
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
	    
	    List<Purchase> purchaseList = mypageService.selectOrderListPage(user_no, startRow, endRow);
        mv.addObject("purchaseList", purchaseList);
        System.out.println(purchaseList + " : 주문목록");
        mv.addObject("pi", pi);
	    mv.setViewName("mypage/orderlist");
		return mv;
	}
	
	@PostMapping("/mypage/pickupModify")
	public String pickupModify(@RequestParam Map<String, String> parameters, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=euc-kr");
		String purchase_no = parameters.get("purchase_no");
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
	
	// 결제취소 
	@PostMapping("/mypage/updateCancelPayment")
	public String CancelPaymentForm(String purchase_no, RedirectAttributes rttr) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    
		int result = mypageService.updateCancelPayment(purchase_no);
		System.out.println("purchase_no : " + purchase_no);
		Point p = mypageService.findPurchasePoint(purchase_no);
		int point = p.getPoint();
		int use_point = p.getUse_point();
		System.out.println("findPurchasePoint : " + p);
		if(result > 0) {
			int result2 = mypageService.memberPointPayCancelDelete(user_no, point, use_point);
			rttr.addFlashAttribute("message", "결제취소 되었습니다.");
		} else {
			rttr.addFlashAttribute("message", "결제취소에 실패하였습니다.");
		}
		return "redirect:/mypage/orderlist";
	}
	
	// review ------------------------------
	// 나의 리뷰 리스트
	@GetMapping("/mypage/review")
	public ModelAndView reviewList(ModelAndView mv, @RequestParam(value="page", required=false) String page) {
	//public Map<String, Object> reviewList(@RequestParam(value="page", required=false) String page) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    
	    int listCount = mypageService.getTotalReviewListCount(user_no);
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 7);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
	    
        List<Review> reviewList = mypageService.findAllReviewPage(user_no, startRow, endRow);
        
		mv.addObject("reviewList", reviewList);
		mv.addObject("pi", pi);
		mv.setViewName("mypage/review");
		return mv;
	}
	
	@PostMapping("/mypage/review")
	@ResponseBody
	public Map<String, Object> pageList(@RequestParam(value="page", required=false) String page, @RequestParam(value="sortStandard", required=false) String sortStandard) throws Exception{
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    
	    if(sortStandard == null) {
	         sortStandard = "recent";
	      }
	    
	    int listCount = mypageService.getTotalReviewListCountTest(sortStandard, user_no);
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 7);
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
        
        List<Review> reviewList = mypageService.findAllReviewPageTest(sortStandard, user_no, startRow, endRow);
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("reviewList", reviewList);
		map.put("pi", pi);
		map.put("sortStandard", sortStandard);
	
		return map;
	}
	
	@GetMapping("/mypage/reviewInsert")
	public void reviewInsertFormPage(int user_no, int od_no, Model model) {
		model.addAttribute("user_no", user_no);
		model.addAttribute("od_no", od_no);
	}
	
	// 리뷰작성
	@PostMapping("/mypage/reviewInsertForm")
	public String reviewInserForm(@RequestParam MultipartFile reviewImg, @RequestParam int od_no, @RequestParam int user_no ,
			@RequestParam double star, @RequestParam String review_text, HttpServletRequest request, Model model, RedirectAttributes rttr) throws IllegalStateException, IOException {
		String review_image;
		if(!reviewImg.isEmpty()) {
			String currentDir = System.getProperty("user.dir");
		      
		      String filePath = currentDir + "\\src\\main\\resources\\static\\images\\uploadFiles\\review";
		      
		      File mkdir = new File(filePath);
		      if(!mkdir.exists()) mkdir.mkdirs();
		      
		      String originFileName = reviewImg.getOriginalFilename();
		      String ext = originFileName.substring(originFileName.lastIndexOf(".")); // 확장자 추출
		      String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		      
		      review_image = "/images/uploadFiles/review/" + savedName;
		      reviewImg.transferTo(new File(filePath + "\\" + savedName));
		} else {
			review_image = null;
		}
		int result = mypageService.reviewInsert(review_text, review_image, star, user_no, od_no);
		if(result > 0) {
			int review_no = mypageService.findReviewNo();
			int result2 = mypageService.insertReviewPoint(user_no, review_no); // 리뷰작성 후 포인트테이블에 insert
			if(result2 > 0) {
				int result3 = mypageService.updateAddMemberPoint(user_no);
				if(result3> 0) {
					System.out.println("success");
				}
			}
			rttr.addFlashAttribute("message", "리뷰가 등록되었습니다.");
		} else {
			System.out.println("insert 실패");
			rttr.addFlashAttribute("message", "리뷰 등록에 실패하였습니다.");
		}
	      
	    return "redirect:/mypage/orderlist";
	}

	// 리뷰 수정 페이지로 이동
	@GetMapping("/mypage/review/{review_no}")
	public String reviewUpdatePage(Model model, @PathVariable int review_no) {
	    
	    Review r = mypageService.selectOneReview(review_no);
	    model.addAttribute("review", r);
	    System.out.println(r);
	    
		return "mypage/reviewUpdate.html";
	}
	
	// 리뷰 수정하기 버튼 클릭
	@PostMapping("/mypage/reviewUpdateForm")
	public String reviewUpdate(HttpServletRequest request, @RequestParam MultipartFile reviewImg, 
			@RequestParam int review_no, @RequestParam String review_text, @RequestParam String old_review_image, RedirectAttributes rttr) throws IllegalStateException, IOException {
		String review_image;
		
		String currentDir = System.getProperty("user.dir");
		String delete_path = currentDir + "\\src\\main\\resources\\static";
		
		if(!reviewImg.isEmpty()) {
			if(!old_review_image.equals("")) {
				File deleteImg = new File(delete_path + old_review_image);
				deleteImg.delete();
			}
			 currentDir = System.getProperty("user.dir");
		      
		      // 사진이 저장되어야 할 경로
		      String filePath = currentDir + "\\src\\main\\resources\\static\\images\\uploadFiles\\review";
		      System.out.println("filePath : " + filePath); 
		      
		      File mkdir = new File(filePath);
		      if(!mkdir.exists()) mkdir.mkdirs();
		      
		      String originFileName = reviewImg.getOriginalFilename();
		      String ext = originFileName.substring(originFileName.lastIndexOf(".")); // 확장자 추출
		      String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		      
		      review_image = "/images/uploadFiles/review/" + savedName;
		      reviewImg.transferTo(new File(filePath + "\\" + savedName));
		} else {
			review_image = null;
		}
	      
		int result = mypageService.reviewUpdate(review_no, review_text, review_image);
		if(result > 0) {
			rttr.addFlashAttribute("message", "리뷰가 수정 되었습니다.");
		} else {
			rttr.addFlashAttribute("message", "리뷰 수정에 실패하였습니다.");
		}
       
      return "redirect:/mypage/review";
	}
	
	
	// 리뷰 삭제
	@GetMapping("/mypage/reviewDelete/{review_no}")
	public String reviewDelete(@PathVariable int review_no, RedirectAttributes rttr) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    
		int result = mypageService.reviewDelete(review_no);
		if(result > 0) {
			rttr.addFlashAttribute("message", "리뷰가 삭제되었습니다.");
			int result2 = mypageService.memberPointReviewDelete(user_no); // 리뷰 삭제 -> 포인트차감
			if(result2 > 0) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		} else {
			rttr.addFlashAttribute("message", "리뷰 삭제에 실패하였습니다.");
		}
		return "redirect:/mypage/review";
	}
	
	//-------------------------------------------
	// 찜 목록 보여주기
	@GetMapping("/mypage/wish")
	public ModelAndView wishListPage(ModelAndView mv, @RequestParam(value="page", required=false) String page) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    int listCount = mypageService.getTotalWishListCount(user_no);
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
	    
        List<Wish> wishList = mypageService.selectWishListPage(user_no, startRow, endRow);
	    mv.addObject("wishList",wishList);
	    mv.addObject("pi", pi);
	    mv.setViewName("mypage/wish");
	    return mv;
	}
	
	// ajax 페이징 
	@PostMapping("/mypage/wish")
	@ResponseBody
	public Map<String, Object> wishList(@RequestParam (value="page", required=false) String page) throws Exception {
		
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    int listCount = mypageService.getTotalWishListCount(user_no);
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
	    
        List<Wish> wishList = mypageService.selectWishListPage(user_no, startRow, endRow);
		
        Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("wishList", wishList);
		map.put("pi", pi);
	
		return map;
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
	
	// 찜 목록에서 장바구니 담기
	@PostMapping("/mypage/wishToCart")
	@ResponseBody
	public void insertWishToCart(HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws IOException {
		String checkValue[] = request.getParameterValues("ck_code");
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		response.setContentType("text/html; charset=utf-8");
		//response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int result = 0;
		for (String product_no : checkValue) {
            result = mypageService.insertWishToCart(user_no, product_no);
        }
		if(result > 0) {
        	out.println("<script>alert('장바구니 담기에 성공하였습니다.'); location.href='/mypage/wish'</script>");
			out.flush();
        } else {
        	out.println("<script>alert('장바구니 담기에 실패하였습니다.'); location.href='/mypage/wish'</script>");
			out.flush();
        }
	}
	
	// 내 포인트 확인
	@GetMapping("/mypage/point")
	public ModelAndView pointListPage(ModelAndView mv, Model model) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    List<Point> pointList = mypageService.pointList(user_no);
	    Member m = mypageService.selectMemberPoint(user_no);
	    model.addAttribute("m", m);
	    List<Purchase> purchaseList = mypageService.purchaseList(user_no);
	    mv.addObject("purchaseList", purchaseList);
	    mv.addObject("pointList", pointList);
	    mv.setViewName("mypage/point");
	    return mv;
	}

}