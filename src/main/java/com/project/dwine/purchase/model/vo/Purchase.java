package com.project.dwine.purchase.model.vo;

import java.util.Date;

public class Purchase {

	private int purchase_no;
	private Date purchase_date;
	private int use_point;
	private String pickup_date;
	private String pickup_place;
	private String pickup_time;
	private String order_status;
	private String status;
	private Date refund_date;
	private int user_no;
	
	public Purchase() {}

	public Purchase(int purchase_no, Date purchase_date, int use_point, String pickup_date, String pickup_place,
			String pickup_time, String order_status, String status, Date refund_date, int user_no) {
		super();
		this.purchase_no = purchase_no;
		this.purchase_date = purchase_date;
		this.use_point = use_point;
		this.pickup_date = pickup_date;
		this.pickup_place = pickup_place;
		this.pickup_time = pickup_time;
		this.order_status = order_status;
		this.status = status;
		this.refund_date = refund_date;
		this.user_no = user_no;
	}

	public int getPurchase_no() {
		return purchase_no;
	}

	public void setPurchase_no(int purchase_no) {
		this.purchase_no = purchase_no;
	}

	public Date getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

	public int getUse_point() {
		return use_point;
	}

	public void setUse_point(int use_point) {
		this.use_point = use_point;
	}

	public String getPickup_date() {
		return pickup_date;
	}

	public void setPickup_date(String pickup_date) {
		this.pickup_date = pickup_date;
	}

	public String getPickup_place() {
		return pickup_place;
	}

	public void setPickup_place(String pickup_place) {
		this.pickup_place = pickup_place;
	}

	public String getPickup_time() {
		return pickup_time;
	}

	public void setPickup_time(String pickup_time) {
		this.pickup_time = pickup_time;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRefund_date() {
		return refund_date;
	}

	public void setRefund_date(Date refund_date) {
		this.refund_date = refund_date;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	@Override
	public String toString() {
		return "Purchase [purchase_no=" + purchase_no + ", purchase_date=" + purchase_date + ", use_point=" + use_point
				+ ", pickup_date=" + pickup_date + ", pickup_place=" + pickup_place + ", pickup_time=" + pickup_time
				+ ", order_status=" + order_status + ", status=" + status + ", refund_date=" + refund_date
				+ ", user_no=" + user_no + "]";
	}
	
	
}
