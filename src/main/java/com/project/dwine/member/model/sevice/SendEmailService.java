package com.project.dwine.member.model.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.dwine.member.dto.MailDto;

@Service
public class SendEmailService {

	private JavaMailSender mailSender;

	@Autowired
	public SendEmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public MailDto emailCheck(String userEmail) {
		String key = getTempKey();
		MailDto dto = new MailDto();
		dto.setAddress(userEmail);
		dto.setTitle("DWine 회원가입 이메일 인증번호입니다.");
		dto.setMessage("안녕하세요. DWine 회원가입 이메일 인증번호는 " + key + " 입니다.");
		dto.setTempKey(key);
		return dto;
	}

	private String getTempKey() {
		String tempNum = "";

		for (int i = 0; i < 6; i++) {
			tempNum += (int) (Math.random() * 10);
		}

		return tempNum;
	}

	public MailDto sendTempPw(String userEmail) {
		String tempPassword = getTempPassword();
		MailDto dto = new MailDto();
		dto.setAddress(userEmail);
		dto.setTitle("DWine 임시 비밀번호입니다.");
		dto.setMessage("안녕하세요. 회원님의 임시 비밀번호는 " + tempPassword + " 입니다." + "\n" + "반드시 비밀번호를 변경해주세요.");
		dto.setTempPassword(tempPassword);
		return dto;
	}

	private String getTempPassword() {
		String tempPassword = "";
		char tempKeyword[] = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')' };

		for (int i = 0; i < 10; i++) {
			int randomIdx = (int) (Math.random() * (tempKeyword.length));
			tempPassword += tempKeyword[randomIdx];
		}
		
		return tempPassword;
	}

	public void mailSend(MailDto dto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(dto.getAddress());
		message.setSubject(dto.getTitle());
		message.setText(dto.getMessage());

		mailSender.send(message);
	}

}
