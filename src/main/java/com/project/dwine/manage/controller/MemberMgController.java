package com.project.dwine.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.manage.model.service.MemberMgService;
import com.project.dwine.member.model.vo.Member;
import com.project.dwine.notice.model.vo.Notice;
import com.project.dwine.paging.PageInfo;


@Controller
@RequestMapping("/manage")
public class MemberMgController {
	private MemberMgService memberMgService;
	
	 @Autowired
	   public MemberMgController(MemberMgService memberMgService) {
	      this.memberMgService = memberMgService;
	   }
	
	//메인
	@GetMapping("/memberMg/main")
	public ModelAndView memberList(ModelAndView mv,@RequestParam(value="page", required=false) String page) {
		
		int listCount = memberMgService.memberMgTotalListCnt();
		//System.out.println(listCount);
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 10);
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
	    
		
		List<Member> memberList = memberMgService.selectMemberMgList(startRow, endRow);
		mv.addObject("memberList", memberList);
		mv.addObject("pi", pi);
		mv.setViewName("manage/memberMg/main");
		return mv;
	}
	
	//페이징, 멤버리스트
	@PostMapping("/memberMg/main")
 	@ResponseBody
 	public Map<String, Object> searchMemberList(@RequestParam(value="searchValue", required=false) String searchValue, @RequestParam(value="page", required=false) String page) throws IOException {
 		
 		int listCount = memberMgService.searchMemberListCnt(searchValue);
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 10);
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
 		List<Member> searchMemberList = memberMgService.searchMemberList(searchValue, startRow, endRow);
 		
 		Map<String, Object> map = new HashMap<>();
 		map.put("pi", pi);
 		map.put("searchValue", searchValue);
 		map.put("searchMemberList", searchMemberList);
 		
 		return map;
 	}
	
	//디테일
	  @GetMapping("memberMg/detail/{user_no}")
	   public String selectMeberByNo(@PathVariable int user_no, Model model) {
	
	     Member member = memberMgService.selectMemberMgByNo(user_no);
	     model.addAttribute("member", member);
	    
	     return "manage/memberMg/detail";
	   }
	
	 //게시물 삭제
	   @GetMapping("memberMg/delete")
	   public String memberMgdelete(String user_no) throws Exception {
		   memberMgService.deleteMemberMg(user_no);
		  
		   System.out.println("컨트롤러" + user_no);
		   
	      return "redirect:/manage/memberMg/main";
	   }

	   //게시물 선택삭제
	   @ResponseBody
	   @RequestMapping(value = "/memberMg/delete")
	   public String memberMgMultiDelte(HttpServletRequest request, HttpServletResponse response) throws Exception {

	       String[] meberDeleteArr = request.getParameterValues("valueArr");
	       
	       int size = meberDeleteArr.length;
	       for(int i = 0; i < size; i++) {
	    	   memberMgService.deleteMemberMg(meberDeleteArr[i]);
	       } 
	       return "redirect:/manage/memberMg/main";
	   }
	   
}
