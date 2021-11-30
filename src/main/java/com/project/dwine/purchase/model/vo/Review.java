package com.project.dwine.purchase.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Review {
	private int review_no;
	private String review_text;
	private String review_image;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date review_date;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date review_modify;
	private double star;
	private String status;
	private int user_no;
	private int od_no;
	private String nickname;
	
	public Review() {}

	public Review(int review_no, String review_text, String review_image, Date review_date, Date review_modify,
			double star, String status, int user_no, int od_no, String nickname) {
		super();
		this.review_no = review_no;
		this.review_text = review_text;
		this.review_image = review_image;
		this.review_date = review_date;
		this.review_modify = review_modify;
		this.star = star;
		this.status = status;
		this.user_no = user_no;
		this.od_no = od_no;
		this.nickname = nickname;
	}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public String getReview_text() {
		return review_text;
	}

	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}

	public String getReview_image() {
		return review_image;
	}

	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}

	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}

	public Date getReview_modify() {
		return review_modify;
	}

	public void setReview_modify(Date review_modify) {
		this.review_modify = review_modify;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getOd_no() {
		return od_no;
	}

	public void setOd_no(int od_no) {
		this.od_no = od_no;
	}

	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "Review [review_no=" + review_no + ", review_text=" + review_text + ", review_image=" + review_image
				+ ", review_date=" + review_date + ", review_modify=" + review_modify + ", star=" + star + ", status="
				+ status + ", user_no=" + user_no + ", od_no=" + od_no + ", nickname=" + nickname + "]";
	}
	
	
}
