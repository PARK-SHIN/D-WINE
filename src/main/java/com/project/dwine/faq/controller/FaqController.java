package com.project.dwine.faq.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dwine.faq.model.service.FaqService;
import com.project.dwine.faq.model.vo.Faq;
import com.project.dwine.member.model.vo.UserImpl;

@Controller
@RequestMapping("/faq")
public class FaqController {

	private FaqService faqService;
	
	@Autowired
	public FaqController(FaqService faqService) {
		this.faqService = faqService;
	}
	
	// 사용자ver FAQ
	@GetMapping("user")
	public ModelAndView userFaqList(ModelAndView mv) {
		
		List<Faq> faqList = faqService.selectFaqList();
		
		mv.addObject("faqList", faqList);
		mv.setViewName("faq/user");
		
		return mv;
	}
	
	// 리스트 불러오기
	@GetMapping("list")
	public ModelAndView selectFaqList(ModelAndView mv) {
		
		List<Faq> faqList = faqService.selectFaqList();
		
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int userNo = user.getUser_no();
		
		mv.addObject("faqList", faqList);
		mv.addObject("userNo", userNo);
		mv.setViewName("faq/list");
		
		return mv;
	}
	
	// 선택 삭제
	@PostMapping(value = "delete")
	@ResponseBody
	public void deleteOrder(HttpServletResponse response, @RequestParam(value = "checkArr[]") List<String> checkArr) throws IOException {
		List<Integer> arr = new ArrayList<>();
		
		for(String s : checkArr) {
			arr.add(Integer.parseInt(s));
		}
		
		int results = 0;
		for(int faqNo : arr) {
			results += faqService.deleteFaq(faqNo);
		}
		
		if(arr.size() == results) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
		}
	}
	
	// 상세보기
	@PostMapping(value = "detail")
	@ResponseBody
	public Faq selectFaq(@RequestParam int faqNo) {
		
		Faq faq = faqService.selectFaq(faqNo);
		
		return faq;
	}
	
	// 게시글 등록
	@PostMapping("regist")
	public String registFaq(HttpServletRequest request, RedirectAttributes rttr) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int userNo = Integer.parseInt(request.getParameter("use_no"));
		
		int result = faqService.registFaq(title, content, userNo);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "게시글 등록에 성공하였습니다.");
		} else {
			rttr.addFlashAttribute("message", "게시글 등록에 실패하였습니다.");
		}
		
		return "redirect:/faq/list";
	}
	
	// 게시글 수정
	@PostMapping("update")
	public String updateFaq(HttpServletRequest request, RedirectAttributes rttr) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int faqNo = Integer.parseInt(request.getParameter("faq_no"));
		
		int result = faqService.updateFaq(title, content, faqNo);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "게시글 수정에 성공하였습니다.");
		} else {
			rttr.addFlashAttribute("message", "게시글 수정에 실패하였습니다.");
		}
		
		return "redirect:/faq/list";
	}
	
	// 제목 검색
	@PostMapping(value = "search")
	@ResponseBody
	public List<Faq> selectSearchList(@RequestParam String searchValue) {
		
		List<Faq> faqList = faqService.selectSearchList(searchValue);
		
		return faqList;
	}
	

	
}
