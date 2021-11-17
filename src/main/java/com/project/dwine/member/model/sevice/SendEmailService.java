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

	public MailDto emailCheck(String userEmaul) {
		String key = getTempKey();
		MailDto dto = new MailDto();
		dto.setAddress(userEmaul);
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

	public void mailSend(MailDto dto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(dto.getAddress());
		message.setSubject(dto.getTitle());
		message.setText(dto.getMessage());

		mailSender.send(message);
	}

}
