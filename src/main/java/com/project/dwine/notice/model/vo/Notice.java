package com.project.dwine.notice.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Notice {
	private int notice_no;
	private String notice_category;
	private String notice_title;
	private String notice_context;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date create_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modify_date;
	private char status;
	private int user_no;
	
	public Notice() {}

	public Notice(int notice_no, String notice_category, String notice_title, String notice_context, Date create_date,
			Date modify_date, char status, int user_no) {
		super();
		this.notice_no = notice_no;
		this.notice_category = notice_category;
		this.notice_title = notice_title;
		this.notice_context = notice_context;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.status = status;
		this.user_no = user_no;
	}
	
	

	public Notice(String notice_category, String notice_title, String notice_context, int user_no) {
		super();
		this.notice_category = notice_category;
		this.notice_title = notice_title;
		this.notice_context = notice_context;
		this.user_no = user_no;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_category() {
		return notice_category;
	}

	public void setNotice_category(String notice_category) {
		this.notice_category = notice_category;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_context() {
		return notice_context;
	}

	public void setNotice_context(String notice_context) {
		this.notice_context = notice_context;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	@Override
	public String toString() {
		return "Notice [notice_no=" + notice_no + ", notice_category=" + notice_category + ", notice_title="
				+ notice_title + ", notice_context=" + notice_context + ", create_date=" + create_date
				+ ", modify_date=" + modify_date + ", status=" + status + ", user_no=" + user_no + "]";
	}

	
	
}
