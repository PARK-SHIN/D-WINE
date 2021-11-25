package com.project.dwine.wish.model.vo;

import java.util.Date;

public class Wish {

	private int wish_no;
	private Date wish_date;
	private int user_no;
	private int product_no;
	
	public Wish() {}

	public Wish(int wish_no, Date wish_date, int user_no, int product_no) {
		super();
		this.wish_no = wish_no;
		this.wish_date = wish_date;
		this.user_no = user_no;
		this.product_no = product_no;
	}

	public int getWish_no() {
		return wish_no;
	}

	public void setWish_no(int wish_no) {
		this.wish_no = wish_no;
	}

	public Date getWish_date() {
		return wish_date;
	}

	public void setWish_date(Date wish_date) {
		this.wish_date = wish_date;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	@Override
	public String toString() {
		return "Wish [wish_no=" + wish_no + ", wish_date=" + wish_date + ", user_no=" + user_no + ", product_no="
				+ product_no + "]";
	}

}
