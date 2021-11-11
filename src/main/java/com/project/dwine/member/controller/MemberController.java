package com.project.dwine.member.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dwine.member.model.sevice.MemberService;
import com.project.dwine.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/login")
	public void loginForm() {
	}

	@GetMapping("/joinConfirm")
	public String joinConfirm() {
		return "member/joinConfirm";
	}

	@GetMapping("/join")
	public String join() {
		return "member/joinForm";
	}

	@PostMapping("/join")
	public String join(Member member) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		String birth = df.format(member.getUser_birth());

		memberService.join(member, birth);

		return "redirect:/member/login";
	}

	@GetMapping("/findId")
	public String findId() {
		return "member/findId";
	}

	@GetMapping("/findPw")
	public String findPw() {
		return "member/findPw";
	}

}
