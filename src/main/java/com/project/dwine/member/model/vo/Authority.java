package com.project.dwine.member.model.vo;

public class Authority {
	private int code;
	private String name;

	public Authority() {
	}

	public Authority(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Authority [code=" + code + ", name=" + name + "]";
	}

}
