package com.project.dwine.member.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.member.dto.CustomOath2User;
import com.project.dwine.member.dto.MailDto;
import com.project.dwine.member.model.sevice.GetNaverAccessToken;
import com.project.dwine.member.model.sevice.MemberService;
import com.project.dwine.member.model.sevice.SendEmailService;
import com.project.dwine.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;
	private SendEmailService sendEmailService;
	private GetNaverAccessToken naverAPI;

	@Autowired
	public MemberController(MemberService memberService, SendEmailService sendEmailService,
			GetNaverAccessToken naverAPI) {
		this.memberService = memberService;
		this.sendEmailService = sendEmailService;
		this.naverAPI = naverAPI;
	}

	@GetMapping("/login")
	public void loginForm() {
	}

	@GetMapping("/kakaologout")
	public String kakaoLogout(HttpSession session, HttpServletResponse response) {
		String accessToken = (String) session.getAttribute("accessToken");
		String reqURL = "https://kapi.kakao.com/v1/user/unlink";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			conn.getResponseCode();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/member/logout";
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
			int checkUser = memberService.checkUser((String) userInfo.get("name"), (String) userInfo.get("mobile"));
			if (checkUser > 0) {
				naverAPI.naverLogout(access_Token);
				request.setAttribute("join", "alreadyJoin");
				return "member/joinConfirm";
			} else {
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
