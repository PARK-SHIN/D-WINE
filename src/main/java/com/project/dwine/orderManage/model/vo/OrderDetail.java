package com.project.dwine.orderManage.model.vo;

public class OrderDetail {

	private int odNo;		// 주문상세번호
	private int odCount;	// 주문수량
	private int odPrice;	// 주문금액
	private int purchaseNo;	// 주문번호
	private int productNo;	// 판매상품번호
	
	public OrderDetail() {}

	public OrderDetail(int odNo, int odCount, int odPrice, int purchaseNo, int productNo) {
		super();
		this.odNo = odNo;
		this.odCount = odCount;
		this.odPrice = odPrice;
		this.purchaseNo = purchaseNo;
		this.productNo = productNo;
	}

	public int getOdNo() {
		return odNo;
	}

	public void setOdNo(int odNo) {
		this.odNo = odNo;
	}

	public int getOdCount() {
		return odCount;
	}

	public void setOdCount(int odCount) {
		this.odCount = odCount;
	}

	public int getOdPrice() {
		return odPrice;
	}

	public void setOdPrice(int odPrice) {
		this.odPrice = odPrice;
	}

	public int getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	@Override
	public String toString() {
		return "OrderDetail [odNo=" + odNo + ", odCount=" + odCount + ", odPrice=" + odPrice + ", purchaseNo="
				+ purchaseNo + ", productNo=" + productNo + "]";
	}

}
