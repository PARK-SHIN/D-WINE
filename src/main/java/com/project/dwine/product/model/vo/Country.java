package com.project.dwine.product.model.vo;

public class Country {

	private int countryNo;			// 국가번호
	private String countryName;		// 국가명
	
	public Country() {}

	public Country(int countryNo, String countryName) {
		super();
		this.countryNo = countryNo;
		this.countryName = countryName;
	}

	public int getCountryNo() {
		return countryNo;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryNo(int countryNo) {
		this.countryNo = countryNo;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "Country [countryNo=" + countryNo + ", countryName=" + countryName + "]";
	}
	
	
	
	
}
