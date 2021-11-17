package com.project.dwine.member.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dwine.member.dto.MailDto;
import com.project.dwine.member.model.sevice.GetAccessToken;
import com.project.dwine.member.model.sevice.MemberService;
import com.project.dwine.member.model.sevice.SendEmailService;
import com.project.dwine.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;
	private SendEmailService sendEmailService;
	private GetAccessToken kakaoAPI;

	@Autowired
	public MemberController(MemberService memberService, SendEmailService sendEmailService, GetAccessToken kakaoAPI) {
		this.memberService = memberService;
		this.sendEmailService = sendEmailService;
		this.kakaoAPI = kakaoAPI;
	}

	@GetMapping("/login")
	public void loginForm() {
	}

	@GetMapping("/joinConfirm")
	public String joinConfirm() {
		return "member/joinConfirm";
	}

	@GetMapping("/kakaojoin")
	public String kakaoJoin(@RequestParam(required = false) String code, HttpSession session,
			HttpServletRequest request) {
		System.out.println("code" + code);

		System.out.println("access_Token : " + session.getAttribute("access_Token"));

		String access_Token = kakaoAPI.getAccessToken(code);
		System.out.println("access_Token : " + access_Token);

		HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_Token);
		System.out.println("login Controller : " + userInfo);

		String user_age = (String) userInfo.get("age_range");

		if (!userInfo.isEmpty()) {
			if (user_age.equals("0~9") || user_age.equals("10~19")) {
				kakaoAPI.kakaoLogout(access_Token);
				request.setAttribute("join", "unableAge");
				return "member/joinForm";
			} else {
				session.setAttribute("access_Token", access_Token);
				session.setAttribute("user_nickname", userInfo.get("nickname"));
				session.setAttribute("age_range", userInfo.get("age_range"));
			}
		}

		return "redirect:/member/join";
	}

	@GetMapping("/join")
	public String join() {
		return "member/joinForm";
	}

	@PostMapping("/join")
	public String join(HttpServletRequest request, HttpSession session, Member member) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		String birth = df.format(member.getUser_birth());

		int result = memberService.join(member, birth);

		if (result > 0) {
			String access_Token = (String) session.getAttribute("access_Token");
			kakaoAPI.kakaoLogout(access_Token);
			session.removeAttribute("access_Token");
			session.removeAttribute("user_nickname");
			session.removeAttribute("age_range");

			return "redirect:/member/login";
		} else {
			request.setAttribute("join", "joinFail");
			return "member/joinForm";
		}

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

		int result = memberService.checkId(userEmail);

		if (result > 0) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("unable");
		} else {
			MailDto dto = sendEmailService.emailCheck(userEmail);
			sendEmailService.mailSend(dto);

			String tempKey = dto.getTempKey();
			System.out.println("temp key : " + tempKey);

			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(tempKey);
		}
	}

	@PostMapping("/nicknameCheck")
	public void nicknameCheck(HttpServletResponse response, String nickName) throws IOException {

		int result = memberService.findMemberByNickname(nickName);

		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);

	}

}
