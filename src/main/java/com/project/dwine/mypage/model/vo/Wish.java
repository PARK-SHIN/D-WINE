package com.project.dwine.mypage.model.vo;

import java.util.Date;

public class Wish {
	private int wish_no;
	private Date wish_date;
	private int user_no;
	private int product_no;
	private String product_kname;
	private String thumbnail;
	
	public Wish() {}

	public Wish(int wish_no, Date wish_date, int user_no, int product_no, String product_kname, String thumbnail) {
		super();
		this.wish_no = wish_no;
		this.wish_date = wish_date;
		this.user_no = user_no;
		this.product_no = product_no;
		this.product_kname = product_kname;
		this.thumbnail = thumbnail;
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

	public String getProduct_kname() {
		return product_kname;
	}

	public void setProduct_kname(String product_kname) {
		this.product_kname = product_kname;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "Wish [wish_no=" + wish_no + ", wish_date=" + wish_date + ", user_no=" + user_no + ", product_no="
				+ product_no + ", product_kname=" + product_kname + ", thumbnail=" + thumbnail + "]";
	}

	
}
