package com.project.dwine.purchase.model.vo;

import java.util.Date;

public class Point {
	
	private int point_no;
	private String content;
	private int point;
	private Date pdate;
	private int user_no;
	private int review_no;
	private String purchase_no;
	
	public Point() {}

	public int getPoint_no() {
		return point_no;
	}

	public void setPoint_no(int point_no) {
		this.point_no = point_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public String getPurchase_no() {
		return purchase_no;
	}

	public void setPurchase_no(String purchase_no) {
		this.purchase_no = purchase_no;
	}

	@Override
	public String toString() {
		return "Point [point_no=" + point_no + ", content=" + content + ", point=" + point + ", pdate=" + pdate
				+ ", user_no=" + user_no + ", review_no=" + review_no + ", purchase_no=" + purchase_no + "]";
	}

	
}
