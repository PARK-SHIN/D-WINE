package com.project.dwine.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.manage.model.service.MemberMgService;
import com.project.dwine.member.model.vo.Member;


@Controller
@RequestMapping("/manage")
public class MemberMgController {
	private MemberMgService memberMgService;
	
	 @Autowired
	   public MemberMgController(MemberMgService memberMgService) {
	      this.memberMgService = memberMgService;
	   }
	
	@GetMapping("/memberMg/main")
	public ModelAndView memberList(ModelAndView mv) {
		List<Member> memberList = memberMgService.selectMemberMgList();
		mv.addObject("memberList", memberList);
		 
		mv.setViewName("manage/memberMg/main");
		return mv;
	}
	
	@GetMapping("memberMg/detail/{user_no}")
	   public String selectMeberByNo(@PathVariable int user_no, Model model) {
	
	     Member member = memberMgService.selectMemberMgByNo(user_no);
	     model.addAttribute("member", member);
	    
	     return "manage/memberMg/detail";
	   }
	
	 // 게시물 삭제
	   @GetMapping("memberMg/delete")
	   public String memberMgdelete(String user_no) throws Exception {
		   memberMgService.deleteMemberMg(user_no);
		   
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
