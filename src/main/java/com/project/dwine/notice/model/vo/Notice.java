package com.project.dwine.notice.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Notice {
	private int notice_no;
	private int notice_category;
	private String notice_title;
	private String notice_context;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date create_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modify_date;
	private char status;
	private int user_no;
	
	public Notice() {}

	public Notice(int notice_no, int notice_category, String title, String context, Date create_date, Date modify_date,
			char status, int user_no) {
		super();
		this.notice_no = notice_no;
		this.notice_category = notice_category;
		this.notice_title = title;
		this.notice_context = context;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.status = status;
		this.user_no = user_no;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public int getNotice_category() {
		return notice_category;
	}

	public void setNotice_category(int notice_category) {
		this.notice_category = notice_category;
	}

	public String getTitle() {
		return notice_title;
	}

	public void setTitle(String title) {
		this.notice_title = title;
	}

	public String getContext() {
		return notice_context;
	}

	public void setContext(String context) {
		this.notice_context = context;
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
		return "Notice [notice_no=" + notice_no + ", notice_category=" + notice_category + ", notice_title=" + notice_title
				+ ", notice_context=" + notice_context + ", create_date=" + create_date + ", modify_date=" + modify_date + ", status="
				+ status + ", user_no=" + user_no + "]";
	}
	
	
	
	
	
	
	
	
}
