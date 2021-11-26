package com.project.dwine.mypage.model.vo;

import java.util.Date;

public class Point {
	
	private int point_no;
	private String content;
	private int point;
	private Date pdate;
	private int review_no;
	private String purchase_no;
	private int use_point;
	private int total;
	
	public Point() {}

	public Point(int point_no, String content, int point, Date pdate, int review_no, String purchase_no, int use_point,
			int total) {
		super();
		this.point_no = point_no;
		this.content = content;
		this.point = point;
		this.pdate = pdate;
		this.review_no = review_no;
		this.purchase_no = purchase_no;
		this.use_point = use_point;
		this.total = total;
	}

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

	public int getUse_point() {
		return use_point;
	}

	public void setUse_point(int use_point) {
		this.use_point = use_point;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Point [point_no=" + point_no + ", content=" + content + ", point=" + point + ", pdate=" + pdate
				+ ", review_no=" + review_no + ", purchase_no=" + purchase_no + ", use_point=" + use_point + ", total="
				+ total + "]";
	}

	
}
