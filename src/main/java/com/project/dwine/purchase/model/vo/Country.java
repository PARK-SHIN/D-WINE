package com.project.dwine.purchase.model.vo;

public class Country {

	private int country_no;
	private String country_name;
	
	public Country() {}

	public Country(int country_no, String country_name) {
		super();
		this.country_no = country_no;
		this.country_name = country_name;
	}

	public int getCountry_no() {
		return country_no;
	}

	public void setCountry_no(int country_no) {
		this.country_no = country_no;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	@Override
	public String toString() {
		return "Country [country_no=" + country_no + ", country_name=" + country_name + "]";
	}
	
	
}
