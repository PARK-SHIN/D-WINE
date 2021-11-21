package com.project.dwine.product.model.vo;

public class Variety {

	private int varietyNo;			// 포도품종 번호
	private String varietyName;		// 포도품종명
	
	public Variety() {}

	public Variety(int varietyNo, String varietyName) {
		super();
		this.varietyNo = varietyNo;
		this.varietyName = varietyName;
	}

	public int getVarietyNo() {
		return varietyNo;
	}

	public String getVarietyName() {
		return varietyName;
	}

	public void setVarietyNo(int varietyNo) {
		this.varietyNo = varietyNo;
	}

	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}

	@Override
	public String toString() {
		return "Variety [varietyNo=" + varietyNo + ", varietyName=" + varietyName + "]";
	}
	
	
	
}
