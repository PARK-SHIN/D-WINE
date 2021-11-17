package com.project.dwine.purchase.model.vo;

public class Hashtag {

	private int hash_no;
	private int hash_type;
	private String hash_name;
	
	public Hashtag() {}

	public Hashtag(int hash_no, int hash_type, String hash_name) {
		super();
		this.hash_no = hash_no;
		this.hash_type = hash_type;
		this.hash_name = hash_name;
	}

	public int getHash_no() {
		return hash_no;
	}

	public void setHash_no(int hash_no) {
		this.hash_no = hash_no;
	}

	public int getHash_type() {
		return hash_type;
	}

	public void setHash_type(int hash_type) {
		this.hash_type = hash_type;
	}

	public String getHash_name() {
		return hash_name;
	}

	public void setHash_name(String hash_name) {
		this.hash_name = hash_name;
	}

	@Override
	public String toString() {
		return "Hashtag [hash_no=" + hash_no + ", hash_type=" + hash_type + ", hash_name=" + hash_name + "]";
	}
	
	
}
