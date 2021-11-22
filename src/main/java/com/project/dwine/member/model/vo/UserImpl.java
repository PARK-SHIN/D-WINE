package com.project.dwine.member.model.vo;

import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserImpl extends User {
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
	private Date create_date;

	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public void setDetails(Member member) {
		this.user_no = member.getUser_no();
		this.user_id = member.getUser_id();
		this.user_pw = member.getUser_pw();
		this.user_name = member.getUser_name();
		this.user_birth = member.getUser_birth();
		this.user_phone = member.getUser_phone();
		this.user_nickname = member.getUser_nickname();
		this.user_point = member.getUser_point();
		this.reported_count = member.getReported_count();
		this.authority = member.getAuthority();
		this.create_date = member.getCreate_date();
	}

	public int getUser_no() {
		return user_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public Date getUser_birth() {
		return user_birth;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public int getUser_point() {
		return user_point;
	}

	public int getReported_count() {
		return reported_count;
	}

	public Authority getAuthority() {
		return authority;
	}

	public Date getCreate_date() {
		return create_date;
	}

}
