package com.project.dwine.personalQnA.model.vo;

import java.util.Date;

public class PersonalA {
	private int a_no;
	private String answer_content;
	private Date anser_date;
	private String answer_status;
	private int qna_no;
	
	public PersonalA() {}
	
	public PersonalA(int a_no, String answer_content, Date anser_date, String answer_status, int qna_no) {
		super();
		this.a_no = a_no;
		this.answer_content = answer_content;
		this.anser_date = anser_date;
		this.answer_status = answer_status;
		this.qna_no = qna_no;
	}

	public int getA_no() {
		return a_no;
	}

	public void setA_no(int a_no) {
		this.a_no = a_no;
	}

	public String getAnswer_content() {
		return answer_content;
	}

	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
	}

	public Date getAnser_date() {
		return anser_date;
	}

	public void setAnser_date(Date anser_date) {
		this.anser_date = anser_date;
	}

	public String getAnswer_status() {
		return answer_status;
	}

	public void setAnswer_status(String answer_status) {
		this.answer_status = answer_status;
	}

	public int getQna_no() {
		return qna_no;
	}

	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}

	@Override
	public String toString() {
		return "PersonalA [a_no=" + a_no + ", answer_content=" + answer_content + ", anser_date=" + anser_date
				+ ", answer_status=" + answer_status + ", qna_no=" + qna_no + "]";
	}
	
	
	
}
