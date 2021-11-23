package com.project.dwine.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
