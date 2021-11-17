package com.project.dwine.purchase.model.vo;

public class Type {

	private int type_no;
	private String type_name;
	
	public Type() {}

	public Type(int type_no, String type_name) {
		super();
		this.type_no = type_no;
		this.type_name = type_name;
	}

	public int getType_no() {
		return type_no;
	}

	public void setType_no(int type_no) {
		this.type_no = type_no;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	@Override
	public String toString() {
		return "Type [type_no=" + type_no + ", type_name=" + type_name + "]";
	}
	
	
}
