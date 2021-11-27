package com.project.dwine.mypage.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.member.model.vo.UserImpl;
import com.project.dwine.mypage.model.service.MypageService;
import com.project.dwine.mypage.model.vo.Pagination;
import com.project.dwine.mypage.model.vo.Payment;
import com.project.dwine.mypage.model.vo.Point;
import com.project.dwine.mypage.model.vo.Purchase;
import com.project.dwine.mypage.model.vo.Review;
import com.project.dwine.mypage.model.vo.Wish;

@Transactional
@Controller
//@RequestMapping("/mypage")
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
			rttr.addFlashAttribute("message", "비밀번호가 변경에 실패하였습니다.");
			System.out.println("비밀번호변경실패");
			return "redirect:/mypage/memberModify";
		}
		
		//return "/mypage/memberModify";
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
	public ModelAndView orderList(ModelAndView mv) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    // 전체 글 개수
	    int orderListCnt = mypageService.orderListCnt(user_no);
	    
	    List<Purchase> purchaseList = mypageService.selectOrderList(user_no);
	    mv.addObject("purchaseList", purchaseList);
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
		int result = mypageService.updateCancelPayment(purchase_no);
		if(result > 0) {
			System.out.println("성공");
			rttr.addFlashAttribute("message", "결제취소 되었습니다.");
		} else {
			System.out.println("실패");
			rttr.addFlashAttribute("message", "결제취소에 실패하였습니다.");
		}
		return "redirect:/mypage/orderlist";
	}
	
	// -----------------------------------------------
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
	
	@GetMapping("/mypage/reviewInsert")
	public void reviewInsertFormPage(int user_no, int od_no, Model model) {
		model.addAttribute("user_no", user_no);
		model.addAttribute("od_no", od_no);
	}
	
	// 리뷰작성
	@PostMapping("/mypage/reviewInsertForm")
	public String reviewInserForm(@RequestParam MultipartFile singleFile, @RequestParam int od_no, @RequestParam int user_no ,
			@RequestParam double star, @RequestParam String review_text, HttpServletRequest request, Model model, RedirectAttributes rttr) {
	      String currentDir = System.getProperty("user.dir");
	      
	      String filePath = currentDir + "\\src\\main\\resources\\static\\images\\uploadFiles\\review";
	      
	      File mkdir = new File(filePath);
	      if(!mkdir.exists()) mkdir.mkdirs();
	      
	      String originFileName = singleFile.getOriginalFilename();
	      String ext = originFileName.substring(originFileName.lastIndexOf(".")); // 확장자 추출
	      String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
	      
	      String review_image = "/images/uploadFiles/review/" + savedName;
	      
	      try {
	    	  singleFile.transferTo(new File(filePath + "\\" + savedName));
	    	  System.out.println("성공");
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
					rttr.addFlashAttribute("message", "리뷰가 등록에 실패하였습니다.");
				}
	      } catch (IllegalStateException | IOException e) {
	         e.printStackTrace();
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
	public String reviewUpdate(HttpServletRequest request, @RequestParam MultipartFile singleFile, 
			@RequestParam int review_no, @RequestParam String review_text, RedirectAttributes rttr) {
		 String currentDir = System.getProperty("user.dir");
	      System.out.println("currentDir" + currentDir); // currentDirC:\Users\OWNER\/\/git\DWine
	      // System.getProperty("user.dir"); => 현재 작업중인 디렉터리 가져오는 메소드
	      
	      // 사진이 저장되어야 할 경로
	      String filePath = currentDir + "\\src\\main\\resources\\static\\images\\uploadFiles\\review";
	      System.out.println("filePath : " + filePath); 
	    //filePath : C:\Users\OWNER/\/git/\/DWine/\/src/\main\resources\static\images/\/uploadFiles\review
	      
	      // 이미지가 저장 될 폴더 생성
	      File mkdir = new File(filePath);
	      // 폴더가 없으면 폴더 생성해라
	      if(!mkdir.exists()) mkdir.mkdirs();
	      
	      String originFileName = singleFile.getOriginalFilename();
	      System.out.println("originFileName : " + originFileName);
	      String ext = originFileName.substring(originFileName.lastIndexOf(".")); // 확장자 추출
	      String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
	      System.out.println("savedName : " + savedName);
	      
	      String review_image = "/images/uploadFiles/review/" + savedName;
	      
	      try {
	    	  // 파일 저장하기 transferTo
	    	  singleFile.transferTo(new File(filePath + "\\" + savedName));
				int result = mypageService.reviewUpdate(review_no, review_text, review_image);
				if(result > 0) {
					rttr.addFlashAttribute("message", "리뷰가 수정 되었습니다.");
				} else {
					rttr.addFlashAttribute("message", "리뷰 수정에 실패하였습니다.");
				}
	      } catch (IllegalStateException | IOException e) {
	         e.printStackTrace();
	      }
	      
	      return "redirect:/mypage/review";
	}
	
	
	// 리뷰 삭제
	@GetMapping("/mypage/reviewDelete/{review_no}")
	public String reviewDelete(@PathVariable int review_no, RedirectAttributes rttr) {
		int result = mypageService.reviewDelete(review_no);
		if(result > 0) {
			rttr.addFlashAttribute("message", "리뷰가 삭제되었습니다.");
		} else {
			rttr.addFlashAttribute("message", "리뷰 삭제에 실패하였습니다.");
		}
		return "redirect:/mypage/review";
	}
	
	//-------------------------------------------
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
	
	// 찜 목록에서 장바구니 담기
	@PostMapping("/mypage/wishToCart")
	public String insertWishToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String checkValue[] = request.getParameterValues("ck_code");
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
		int result = 0;
		for (String product_no : checkValue) {
            result = mypageService.insertWishToCart(user_no, product_no);
        }
		if(result > 0) {
        	out.println("<script>alert('장바구니 담기에 성공하였습니다.'); location.href='/mypage/wish' </script>");
			out.flush();
			return "/mypage/wish";
        } else {
        	out.println("<script>alert('장바구니 담기에 실패하였습니다.'); location.href='/mypage/wish' </script>");
			out.flush();
			return "/mypage/wish";
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
	    System.out.println(m);
	    List<Purchase> purchaseList = mypageService.purchaseList(user_no);
	    mv.addObject("purchaseList", purchaseList);
	    mv.addObject("pointList", pointList);
	    System.out.println(pointList);
	    mv.setViewName("mypage/point");
	    return mv;
	}

}