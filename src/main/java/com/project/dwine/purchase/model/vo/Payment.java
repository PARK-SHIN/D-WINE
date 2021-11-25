package com.project.dwine.purchase.model.vo;

import java.util.Date;

public class Payment {

	private int pay_no;
	private Date pay_date;
	private String pay_method;
	private int purchase_no;
	
	public Payment() {}

	public Payment(int pay_no, Date pay_date, String pay_method, int purchase_no) {
		super();
		this.pay_no = pay_no;
		this.pay_date = pay_date;
		this.pay_method = pay_method;
		this.purchase_no = purchase_no;
	}

	public int getPay_no() {
		return pay_no;
	}

	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public int getPurchase_no() {
		return purchase_no;
	}

	public void setPurchase_no(int purchase_no) {
		this.purchase_no = purchase_no;
	}

	@Override
	public String toString() {
		return "Payment [pay_no=" + pay_no + ", pay_date=" + pay_date + ", pay_method=" + pay_method + ", purchase_no="
				+ purchase_no + "]";
	}

}
