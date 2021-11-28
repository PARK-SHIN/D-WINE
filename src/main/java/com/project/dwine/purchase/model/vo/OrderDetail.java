package com.project.dwine.purchase.model.vo;

public class OrderDetail {

	private int od_no;
	private int od_count;
	private int od_price;
	private String purchase_no;
	private int product_no;
	
	public OrderDetail() {}

	public OrderDetail(int od_no, int od_count, int od_price, String purchase_no, int product_no) {
		super();
		this.od_no = od_no;
		this.od_count = od_count;
		this.od_price = od_price;
		this.purchase_no = purchase_no;
		this.product_no = product_no;
	}

	public int getOd_no() {
		return od_no;
	}

	public void setOd_no(int od_no) {
		this.od_no = od_no;
	}

	public int getOd_count() {
		return od_count;
	}

	public void setOd_count(int od_count) {
		this.od_count = od_count;
	}

	public int getOd_price() {
		return od_price;
	}

	public void setOd_price(int od_price) {
		this.od_price = od_price;
	}

	public String getPurchase_no() {
		return purchase_no;
	}

	public void setPurchase_no(String purchase_no) {
		this.purchase_no = purchase_no;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	@Override
	public String toString() {
		return "OrderDetail [od_no=" + od_no + ", od_count=" + od_count + ", od_price=" + od_price + ", purchase_no="
				+ purchase_no + ", product_no=" + product_no + "]";
	}

}
