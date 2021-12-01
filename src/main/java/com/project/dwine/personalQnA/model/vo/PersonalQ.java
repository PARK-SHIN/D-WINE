package com.project.dwine.personalQnA.model.vo;

import java.util.Date;
import java.util.List;

public class PersonalQ {
	private int qna_no;
	private String qna_title;
	private String qna_content;
	private String qna_image;
	private String qna_status; // 삭제여부
	private Date qna_date;
	private String qna_answer; // 답변여부
	private int user_no;

	private Integer a_no;
	private String answer_content;
	private Date anser_date;
	private String answer_status;
	private String user_id;
	private String user_name;

//	private List<PersonalA> answerList;

	public PersonalQ() {
	}

	public PersonalQ(int qna_no, String qna_title, String qna_content, String qna_image, String qna_status,
			Date qna_date, String qna_answer, int user_no, Integer a_no, String answer_content, Date anser_date,
			String answer_status, String user_id, String user_name) {
		super();
		this.qna_no = qna_no;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_image = qna_image;
		this.qna_status = qna_status;
		this.qna_date = qna_date;
		this.qna_answer = qna_answer;
		this.user_no = user_no;
		this.a_no = a_no;
		this.answer_content = answer_content;
		this.anser_date = anser_date;
		this.answer_status = answer_status;
		this.user_id = user_id;
		this.user_name = user_name;
	}

	public int getQna_no() {
		return qna_no;
	}

	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}

	public String getQna_title() {
		return qna_title;
	}

	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public String getQna_image() {
		return qna_image;
	}

	public void setQna_image(String qna_image) {
		this.qna_image = qna_image;
	}

	public String getQna_status() {
		return qna_status;
	}

	public void setQna_status(String qna_status) {
		this.qna_status = qna_status;
	}

	public Date getQna_date() {
		return qna_date;
	}

	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}

	public String getQna_answer() {
		return qna_answer;
	}

	public void setQna_answer(String qna_answer) {
		this.qna_answer = qna_answer;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public Integer getA_no() {
		return a_no;
	}

	public void setA_no(Integer a_no) {
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public String toString() {
		return "PersonalQ [qna_no=" + qna_no + ", qna_title=" + qna_title + ", qna_content=" + qna_content
				+ ", qna_image=" + qna_image + ", qna_status=" + qna_status + ", qna_date=" + qna_date + ", qna_answer="
				+ qna_answer + ", user_no=" + user_no + ", a_no=" + a_no + ", answer_content=" + answer_content
				+ ", anser_date=" + anser_date + ", answer_status=" + answer_status + ", user_id=" + user_id
				+ ", user_name=" + user_name + "]";
	}

}
