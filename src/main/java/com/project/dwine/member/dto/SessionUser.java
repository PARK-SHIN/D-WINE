package com.project.dwine.member.dto;

import java.io.Serializable;

public class SessionUser implements Serializable {

	private String name;
	private String email;
	private String age;

	public SessionUser(tempUser user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.age = user.getAge();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	
	
	
}