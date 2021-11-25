package com.project.dwine.manage.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Review {
	private int review_no;
	private String review_text;
	private String review_image;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date review_date;
	private Date review_modify;
	private double star;
	private String status;
	private int user_no;
	private int od_no;
	private String product_kname;
	
	//추가테스트컬럼
	private String user_id1;
	private String user_id2;
	private int report_no;
	private int reason_no;
	private int reporter_no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date report_date;
	private int count;
	private String reason_context;
	
	public Review() {}

	public Review(int review_no, String review_text, String review_image, Date review_date, Date review_modify,
			double star, String status, int user_no, int od_no, String product_kname, String user_id1, String user_id2,
			int report_no, int reason_no, int reporter_no, Date report_date, int count, String reason_context) {
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
		this.product_kname = product_kname;
		this.user_id1 = user_id1;
		this.user_id2 = user_id2;
		this.report_no = report_no;
		this.reason_no = reason_no;
		this.reporter_no = reporter_no;
		this.report_date = report_date;
		this.count = count;
		this.reason_context = reason_context;
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

	public String getProduct_kname() {
		return product_kname;
	}

	public void setProduct_kname(String product_kname) {
		this.product_kname = product_kname;
	}

	public String getUser_id1() {
		return user_id1;
	}

	public void setUser_id1(String user_id1) {
		this.user_id1 = user_id1;
	}

	public String getUser_id2() {
		return user_id2;
	}

	public void setUser_id2(String user_id2) {
		this.user_id2 = user_id2;
	}

	public int getReport_no() {
		return report_no;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	public int getReason_no() {
		return reason_no;
	}

	public void setReason_no(int reason_no) {
		this.reason_no = reason_no;
	}

	public int getReporter_no() {
		return reporter_no;
	}

	public void setReporter_no(int reporter_no) {
		this.reporter_no = reporter_no;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getReason_context() {
		return reason_context;
	}

	public void setReason_context(String reason_context) {
		this.reason_context = reason_context;
	}

	@Override
	public String toString() {
		return "Review [review_no=" + review_no + ", review_text=" + review_text + ", review_image=" + review_image
				+ ", review_date=" + review_date + ", review_modify=" + review_modify + ", star=" + star + ", status="
				+ status + ", user_no=" + user_no + ", od_no=" + od_no + ", product_kname=" + product_kname
				+ ", user_id1=" + user_id1 + ", user_id2=" + user_id2 + ", report_no=" + report_no + ", reason_no="
				+ reason_no + ", reporter_no=" + reporter_no + ", report_date=" + report_date + ", count=" + count
				+ ", reason_context=" + reason_context + "]";
	}

	
}
