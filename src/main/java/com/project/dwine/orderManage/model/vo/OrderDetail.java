package com.project.dwine.orderManage.model.vo;

public class OrderDetail {

	private int purchaseNo;
	private int productNo;			// 상품번호
	private String typeName;		// 와인종류
	private String productKname;	// 와인명
	private int odCount;			// 주문수량
	private int odPrice;			// 와인가격
	
	public OrderDetail() {}

	public OrderDetail(int purchaseNo, int productNo, String typeName, String productKname, int odCount, int odPrice) {
		super();
		this.purchaseNo = purchaseNo;
		this.productNo = productNo;
		this.typeName = typeName;
		this.productKname = productKname;
		this.odCount = odCount;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getProductKname() {
		return productKname;
	}

	public void setProductKname(String productKname) {
		this.productKname = productKname;
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

	@Override
	public String toString() {
		return "OrderDetail [purchaseNo=" + purchaseNo + ", productNo=" + productNo + ", typeName=" + typeName
				+ ", productKname=" + productKname + ", odCount=" + odCount + ", odPrice=" + odPrice + "]";
	}

	
}
