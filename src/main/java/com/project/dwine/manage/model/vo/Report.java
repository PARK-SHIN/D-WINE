package com.project.dwine.manage.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.mypage.model.vo.Review;

public class Report {
	private int report_no;
	private int user_no;
	private int reason_no;
	private int review_no;
	private int reporter_no;
	private int count;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date report_date;

	private String user_id1;
	private String user_id2;
	private String review_text;
	private Date review_date;
	private String reason_context;
	
	
	
	public Report() {}



	public Report(int report_no, int user_no, int reason_no, int review_no, int reporter_no, int count,
			Date report_date, String user_id1, String user_id2, String review_text, Date review_date,
			String reason_context) {
		super();
		this.report_no = report_no;
		this.user_no = user_no;
		this.reason_no = reason_no;
		this.review_no = review_no;
		this.reporter_no = reporter_no;
		this.count = count;
		this.report_date = report_date;
		this.user_id1 = user_id1;
		this.user_id2 = user_id2;
		this.review_text = review_text;
		this.review_date = review_date;
		this.reason_context = reason_context;
	}



	public int getReport_no() {
		return report_no;
	}



	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}



	public int getUser_no() {
		return user_no;
	}



	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}



	public int getReason_no() {
		return reason_no;
	}



	public void setReason_no(int reason_no) {
		this.reason_no = reason_no;
	}



	public int getReview_no() {
		return review_no;
	}



	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}



	public int getReporter_no() {
		return reporter_no;
	}



	public void setReporter_no(int reporter_no) {
		this.reporter_no = reporter_no;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public Date getReport_date() {
		return report_date;
	}



	public void setReport_date(Date report_date) {
		this.report_date = report_date;
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



	public String getReview_text() {
		return review_text;
	}



	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}



	public Date getReview_date() {
		return review_date;
	}



	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}



	public String getReason_context() {
		return reason_context;
	}



	public void setReason_context(String reason_context) {
		this.reason_context = reason_context;
	}



	@Override
	public String toString() {
		return "Report [report_no=" + report_no + ", user_no=" + user_no + ", reason_no=" + reason_no + ", review_no="
				+ review_no + ", reporter_no=" + reporter_no + ", count=" + count + ", report_date=" + report_date
				+ ", user_id1=" + user_id1 + ", user_id2=" + user_id2 + ", review_text=" + review_text
				+ ", review_date=" + review_date + ", reason_context=" + reason_context + "]";
	}





	


	
	
	
}