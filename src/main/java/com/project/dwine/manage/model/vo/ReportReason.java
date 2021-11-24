package com.project.dwine.manage.model.vo;

public class ReportReason {
	private int reason_no;
	private String reason_context;
	
	public ReportReason() {}

	public ReportReason(int reason_no, String reason_context) {
		super();
		this.reason_no = reason_no;
		this.reason_context = reason_context;
	}

	public int getReason_no() {
		return reason_no;
	}

	public void setReason_no(int reason_no) {
		this.reason_no = reason_no;
	}

	public String getReason_context() {
		return reason_context;
	}

	public void setReason_context(String reason_context) {
		this.reason_context = reason_context;
	}

	@Override
	public String toString() {
		return "ReportReason [reason_no=" + reason_no + ", reason_context=" + reason_context + "]";
	}

	
	

}
