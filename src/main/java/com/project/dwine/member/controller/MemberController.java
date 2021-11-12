package com.project.dwine.member.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dwine.member.dto.MailDto;
import com.project.dwine.member.model.sevice.MemberService;
import com.project.dwine.member.model.sevice.SendEmailService;
import com.project.dwine.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;
	private SendEmailService sendEmailService;

	@Autowired
	public MemberController(MemberService memberService, SendEmailService sendEmailService) {
		this.memberService = memberService;
		this.sendEmailService = sendEmailService;
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

	@PostMapping("/email")
	public void sendEmail(HttpServletResponse response, String userEmail) throws IOException {
		MailDto dto = sendEmailService.emailCheck(userEmail);
		sendEmailService.mailSend(dto);

		String tempKey = dto.getTempKey();
		System.out.println("temp key : " + tempKey);

		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(tempKey);

	}

	@PostMapping("/nicknameCheck")
	public void nicknameCheck(HttpServletResponse response, String nickName) throws IOException {

		int result = memberService.findMemberByNickname(nickName);

		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);

	}

}
