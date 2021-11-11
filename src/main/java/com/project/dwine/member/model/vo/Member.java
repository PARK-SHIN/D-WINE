package com.project.dwine.member.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Member {
	private int user_no;
	private String user_id;
	private String user_pw;
	private String user_name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date user_birth;
	private String user_phone;
	private String user_nickname;
	private int user_point;
	private int reported_count;
	private Authority authority;

	public Member() {
	}

	public Member(int user_no, String user_id, String user_pw, String user_name, Date user_birth, String user_phone,
			String user_nickname, int user_point, int reported_count, Authority authority) {
		this.user_no = user_no;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_phone = user_phone;
		this.user_nickname = user_nickname;
		this.user_point = user_point;
		this.reported_count = reported_count;
		this.authority = authority;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(Date user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public int getUser_point() {
		return user_point;
	}

	public void setUser_point(int user_point) {
		this.user_point = user_point;
	}

	public int getReported_count() {
		return reported_count;
	}

	public void setReported_count(int reported_count) {
		this.reported_count = reported_count;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Member [user_no=" + user_no + ", user_id=" + user_id + ", user_pw=" + user_pw + ", user_name="
				+ user_name + ", user_birth=" + user_birth + ", user_phone=" + user_phone + ", user_nickname="
				+ user_nickname + ", user_point=" + user_point + ", reported_count=" + reported_count + ", authority="
				+ authority + "]";
	}

}
