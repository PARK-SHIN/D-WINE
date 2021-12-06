package com.project.dwine.salesInquiry.model.vo;

import java.util.Date;

public class Total {
	
	private int totalOrder;
	private int totalPickup;
	private int totalPrice;
	private int totalRefund;
	private int salesCount;
	private String productName;
	private Date daily;
	private int point;
	private int year;
	private int month;
	
	public Total() {}

	public Total(int totalOrder, int totalPickup, int totalPrice, int totalRefund, int salesCount, String productName,
			Date daily, int point, int year, int month) {
		super();
		this.totalOrder = totalOrder;
		this.totalPickup = totalPickup;
		this.totalPrice = totalPrice;
		this.totalRefund = totalRefund;
		this.salesCount = salesCount;
		this.productName = productName;
		this.daily = daily;
		this.point = point;
		this.year = year;
		this.month = month;
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

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "Total [totalOrder=" + totalOrder + ", totalPickup=" + totalPickup + ", totalPrice=" + totalPrice
				+ ", totalRefund=" + totalRefund + ", salesCount=" + salesCount + ", productName=" + productName
				+ ", daily=" + daily + ", point=" + point + ", year=" + year + ", month=" + month + "]";
	}

}
