package com.project.dwine.orderManage.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Purchase {
	
	private int purchaseNo;			// 주문번호
	@DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
	private Date purchaseDate;		// 주문일시
	private int usePoint;			// 사용포인트
	private int purchasePrice;		// 주문총액
	private String pickupDate;		// 픽업날짜
	private String pickupPlace;		// 픽업장소
	private String pickupTime;		// 픽업시간
	private String orderStatus;		// 주문상태 ex)결제완료
	private String status;			// 상태 	  ex)Y
	private Date refundDate;		// 환불일시
	private int userNo;				// 회원번호
	private String userName;		// 회원이름
	private String userPhone;		// 회원전화번호
	private int productNo;			// 상품번호
	private String typeName;		// 와인종류
	private String productKname;	// 와인명
	private int odCount;			// 주문수량
	private int odPrice;			// 와인가격
	
	public Purchase() {}

	public Purchase(int purchaseNo, Date purchaseDate, int usePoint, int purchasePrice, String pickupDate,
			String pickupPlace, String pickupTime, String orderStatus, String status, Date refundDate, int userNo,
			String userName) {
		super();
		this.purchaseNo = purchaseNo;
		this.purchaseDate = purchaseDate;
		this.usePoint = usePoint;
		this.purchasePrice = purchasePrice;
		this.pickupDate = pickupDate;
		this.pickupPlace = pickupPlace;
		this.pickupTime = pickupTime;
		this.orderStatus = orderStatus;
		this.status = status;
		this.refundDate = refundDate;
		this.userNo = userNo;
		this.userName = userName;
	}

	public Purchase(int purchaseNo, Date purchaseDate, int usePoint, int purchasePrice, String pickupDate,
			String pickupPlace, String pickupTime, String orderStatus, String status, Date refundDate, int userNo,
			String userName, String userPhone, int productNo, String typeName, String productKname, int odCount,
			int odPrice) {
		super();
		this.purchaseNo = purchaseNo;
		this.purchaseDate = purchaseDate;
		this.usePoint = usePoint;
		this.purchasePrice = purchasePrice;
		this.pickupDate = pickupDate;
		this.pickupPlace = pickupPlace;
		this.pickupTime = pickupTime;
		this.orderStatus = orderStatus;
		this.status = status;
		this.refundDate = refundDate;
		this.userNo = userNo;
		this.userName = userName;
		this.userPhone = userPhone;
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

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getUsePoint() {
		return usePoint;
	}

	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}

	public String getPickupPlace() {
		return pickupPlace;
	}

	public void setPickupPlace(String pickupPlace) {
		this.pickupPlace = pickupPlace;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
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
		return "Purchase [purchaseNo=" + purchaseNo + ", purchaseDate=" + purchaseDate + ", usePoint=" + usePoint
				+ ", purchasePrice=" + purchasePrice + ", pickupDate=" + pickupDate + ", pickupPlace=" + pickupPlace
				+ ", pickupTime=" + pickupTime + ", orderStatus=" + orderStatus + ", status=" + status + ", refundDate="
				+ refundDate + ", userNo=" + userNo + ", userName=" + userName + ", userPhone=" + userPhone
				+ ", productNo=" + productNo + ", typeName=" + typeName + ", productKname=" + productKname
				+ ", odCount=" + odCount + ", odPrice=" + odPrice + "]";
	}

}
