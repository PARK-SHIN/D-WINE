package com.project.dwine.faq.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.project.dwine.paging.PageInfo;
import com.project.dwine.salesInquiry.model.vo.Total;

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
	public ModelAndView userFaqList(ModelAndView mv, @RequestParam(value="page", required=false) String page,
													 @RequestParam(value="searchValue", required=false) String searchValue) {
		
		int faqCount = faqService.getFaqCount(searchValue);
		
		int resultPage = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		PageInfo pi = new PageInfo(resultPage, faqCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
        		
		List<Faq> faqList = faqService.selectFaqList(searchValue, startRow, endRow);
		
		mv.addObject("pi", pi);
		mv.addObject("faqList", faqList);
		mv.setViewName("faq/user");
		
		return mv;
	}
	
	@PostMapping("paging")
	@ResponseBody
	public Map<String, Object> searchFaqList(Model model, @RequestParam(value="page", required=false) String page,
														  @RequestParam(value="searchValue", required=false) String searchValue) {
					
		int faqCount = faqService.getFaqCount(searchValue);
	
		int resultPage = 1;
		if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		PageInfo pi = new PageInfo(resultPage, faqCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
        		
		List<Faq> faqList = faqService.selectFaqList(searchValue, startRow, endRow);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pi", pi);
		map.put("faqList", faqList);
		
		return map;
	}
	
	// 리스트 불러오기
	@GetMapping("list")
	public ModelAndView selectFaqList(ModelAndView mv, @RequestParam(value="page", required=false) String page,
			 										   @RequestParam(value="searchValue", required=false) String searchValue) {
		
		int faqCount = faqService.getFaqCount(searchValue);
		
		int resultPage = 1;
		if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		PageInfo pi = new PageInfo(resultPage, faqCount, 10, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
        		
		List<Faq> faqList = faqService.selectFaqList(searchValue, startRow, endRow);
		
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int userNo = user.getUser_no();
		
		mv.addObject("pi", pi);
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
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int userNo = user.getUser_no();
		
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
	
}
