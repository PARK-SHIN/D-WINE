package com.project.dwine.member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dwine.member.dto.MailDto;
import com.project.dwine.member.model.sevice.GetKakaoAccessToken;
import com.project.dwine.member.model.sevice.GetNaverAccessToken;
import com.project.dwine.member.model.sevice.MemberService;
import com.project.dwine.member.model.sevice.SendEmailService;
import com.project.dwine.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;
	private SendEmailService sendEmailService;
	private GetKakaoAccessToken kakaoAPI;
	private GetNaverAccessToken naverAPI;

	@Autowired
	public MemberController(MemberService memberService, SendEmailService sendEmailService,
			GetKakaoAccessToken kakaoAPI, GetNaverAccessToken naverAPI) {
		this.memberService = memberService;
		this.sendEmailService = sendEmailService;
		this.kakaoAPI = kakaoAPI;
		this.naverAPI = naverAPI;
	}

	@GetMapping("/login")
	public void loginForm() {
	}

	@GetMapping("/joinConfirm")
	public String joinConfirm(HttpSession session) {

		session.removeAttribute("access_Token");
		session.removeAttribute("user_nickname");
		session.removeAttribute("age_range");
		session.removeAttribute("name");
		session.removeAttribute("birth");
		session.removeAttribute("mobile");

		return "member/joinConfirm";
	}

	@GetMapping("/kakaojoin")
	public String kakaoJoin(@RequestParam(required = false) String code, HttpSession session,
			HttpServletRequest request) {
		String access_Token = kakaoAPI.getAccessToken(code);
		HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_Token);
		String user_age = "";
		if (userInfo.get("age_range") == null) {
			kakaoAPI.kakaoLogout(access_Token);
			request.setAttribute("join", "unknownAge");
			return "member/joinConfirm";
		} else {
			user_age = (String) userInfo.get("age_range");
		}
		if (!userInfo.isEmpty()) {
			if (user_age.equals("0~9") || user_age.equals("10~19")) {
				request.setAttribute("join", "unableAge");
				return "member/joinForm";
			} else {
				session.setAttribute("access_Token", access_Token);
				session.setAttribute("user_nickname", userInfo.get("nickname"));
				session.setAttribute("age_range", userInfo.get("age_range"));
				kakaoAPI.kakaoLogout(access_Token);
			}
		}

		return "redirect:/member/join";
	}

	@GetMapping("/naverjoin")
	public String naverJoin(@RequestParam(required = false) String code, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String access_Token = naverAPI.getAccessToken(code);
		if (access_Token == null) {
			request.setAttribute("join", "cancleConfirm");
			return "member/joinConfirm";
		}
		HashMap<String, Object> userInfo = naverAPI.getUserInfo(access_Token);

		if (userInfo != null) {
			String age = (String) userInfo.get("age");
			if (age.equals("0-9") || age.equals("10-19")) {
				request.setAttribute("join", "unableAge");
				return "member/joinConfirm";
			} else {
				String birth = userInfo.get("birthyear") + "-" + userInfo.get("birthday");
				session.setAttribute("name", userInfo.get("name"));
				session.setAttribute("birth", birth);
				session.setAttribute("mobile", userInfo.get("mobile"));
				naverAPI.naverLogout(access_Token);
			}
		} else {
			naverAPI.naverLogout(access_Token);
			request.setAttribute("join", "disagreeInfo");
			return "member/joinConfirm";
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
			session.removeAttribute("access_Token");
			session.removeAttribute("user_nickname");
			session.removeAttribute("age_range");
			session.removeAttribute("name");
			session.removeAttribute("birth");
			session.removeAttribute("mobile");

			return "redirect:/member/login";
		} else {
			request.setAttribute("join", "joinFail");
			return "member/joinForm";
		}

	}

	@GetMapping("/findId")
	public void findId() {
	}

	@PostMapping("/findId")
	public String findId(HttpServletRequest request, HttpSession session, Member member) {

		String userId = memberService.findMemberId(member.getUser_name(), member.getUser_phone());

		if (userId == null) {
			request.setAttribute("findId", "noMember");
			return "member/findId";
		} else {
			request.setAttribute("userId", userId);
		}
		return "member/findId";

	}

	@GetMapping("/findPw")
	public void findPw() {
	}

	@PostMapping("/findPw")
	public String findPw(HttpServletRequest request, HttpSession session, Member member) {

		int findMemberPw = memberService.findMemberByName_Id(member.getUser_name(), member.getUser_id());

		if (findMemberPw > 0) {
			MailDto dto = sendEmailService.sendTempPw(member.getUser_id());
			sendEmailService.mailSend(dto);

			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String tempPw = bCryptPasswordEncoder.encode(dto.getTempPassword());
			if (bCryptPasswordEncoder.matches(dto.getTempPassword(), tempPw)) {
				memberService.updateTempPassword(member.getUser_id(), tempPw);
				request.setAttribute("findPw", "modifyPw");
			}
		} else {
			request.setAttribute("findPw", "noMember");
			return "member/findPw";

		}
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
