package com.project.dwine.purchase.model.vo;

public class Variety {

	private int variety_no;
	private String variety_name;
	
	public Variety() {}

	public Variety(int variety_no, String variety_name) {
		super();
		this.variety_no = variety_no;
		this.variety_name = variety_name;
	}

	public int getVariety_no() {
		return variety_no;
	}

	public void setVariety_no(int variety_no) {
		this.variety_no = variety_no;
	}

	public String getVariety_name() {
		return variety_name;
	}

	public void setVariety_name(String variety_name) {
		this.variety_name = variety_name;
	}

	@Override
	public String toString() {
		return "Variety [variety_no=" + variety_no + ", variety_name=" + variety_name + "]";
	}
	
	
}
