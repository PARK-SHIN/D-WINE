package com.project.dwine.salesInquiry.model.vo;

import java.util.Date;

public class Total {
	
	private int totalOrder;
	private int totalPickup;
	private int totalPrice;
	private int totalRefund;
	private Date daily;
	private int point;
	
	public Total() {}

	public Total(int totalOrder, int totalPickup, int totalPrice, int totalRefund, Date daily, int point) {
		super();
		this.totalOrder = totalOrder;
		this.totalPickup = totalPickup;
		this.totalPrice = totalPrice;
		this.totalRefund = totalRefund;
		this.daily = daily;
		this.point = point;
	}

	public int getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(int totalOrder) {
		this.totalOrder = totalOrder;
	}

	public int getTotalPickup() {
		return totalPickup;
	}

	public void setTotalPickup(int totalPickup) {
		this.totalPickup = totalPickup;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalRefund() {
		return totalRefund;
	}

	public void setTotalRefund(int totalRefund) {
		this.totalRefund = totalRefund;
	}

	public Date getDaily() {
		return daily;
	}

	public void setDaily(Date daily) {
		this.daily = daily;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Total [totalOrder=" + totalOrder + ", totalPickup=" + totalPickup + ", totalPrice=" + totalPrice
				+ ", totalRefund=" + totalRefund + ", daily=" + daily + ", point=" + point + "]";
	}

}
