package com.project.dwine.faq.model.vo;

import java.util.Date;

public class Faq {

	private int faqNo;
	private String faqTitle;
	private String faqContent;
	private Date createDate;
	private Date updateDate;
	private String status;
	private int userNo;
	
	public Faq() {}

	public Faq(int faqNo, String faqTitle, String faqContent, Date createDate, Date updateDate, String status,
			int userNo) {
		super();
		this.faqNo = faqNo;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.userNo = userNo;
	}

	public int getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "Faq [faqNo=" + faqNo + ", faqTitle=" + faqTitle + ", faqContent=" + faqContent + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", status=" + status + ", userNo=" + userNo + "]";
	}
}
