package com.project.dwine.manage.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Inventory {
	
	//join
	private int totalStock;	//창고에 있는 상품의 종류 수
	private int totalShop;	//shop에 올라간 상품의 종류 수
	private int todayReceiving; //오늘 입고주문건 수 
	
	//inven
	private int inven_no;	//입고번호
	private int inven_count; 	//입고수량
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inven_date;	//입고날짜
	private char status; 		//입고상태	
	private int product_no;		//상품번호
	
	//count들 
	private int inven_price; //입고가 = 단가 * 입고수량
	private int all_count; // 상품의 현재수량 + 입고수량  =  총수량
	
	//join product
	private int cost_price; //상품원가
	private int sale_price; //상품 가격
	private int product_count; //현재수량
	private String product_kname;
	private String product_ename;
	
	
	
	public Inventory() {}



	public Inventory(int totalStock, int totalShop, int todayReceiving, int inven_no, int inven_count, Date inven_date,
			char status, int product_no, int inven_price, int all_count, int cost_price, int sale_price,int product_count, 
			String product_kname, String product_ename) {
			super();
			this.totalStock = totalStock;
			this.totalShop = totalShop;
			this.todayReceiving = todayReceiving;
			this.inven_no = inven_no;
			this.inven_count = inven_count;
			this.inven_date = inven_date;
			this.status = status;
			this.product_no = product_no;
			this.inven_price = inven_price;
			this.all_count = all_count;
			this.cost_price = cost_price;
			this.sale_price = sale_price;
			this.product_count = product_count;
			this.product_kname = product_kname;
			this.product_ename = product_ename;
	}



	public int getTotalStock() {
		return totalStock;
	}



	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}



	public int getTotalShop() {
		return totalShop;
	}



	public void setTotalShop(int totalShop) {
		this.totalShop = totalShop;
	}



	public int getTodayReceiving() {
		return todayReceiving;
	}



	public void setTodayReceiving(int todayReceiving) {
		this.todayReceiving = todayReceiving;
	}



	public int getInven_no() {
		return inven_no;
	}



	public void setInven_no(int inven_no) {
		this.inven_no = inven_no;
	}



	public int getInven_count() {
		return inven_count;
	}



	public void setInven_count(int inven_count) {
		this.inven_count = inven_count;
	}



	public Date getInven_date() {
		return inven_date;
	}



	public void setInven_date(Date inven_date) {
		this.inven_date = inven_date;
	}



	public char getStatus() {
		return status;
	}



	public void setStatus(char status) {
		this.status = status;
	}



	public int getProduct_no() {
		return product_no;
	}



	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}



	public int getInven_price() {
		return inven_price;
	}



	public void setInven_price(int inven_price) {
		this.inven_price = inven_price;
	}



	public int getAll_count() {
		return all_count;
	}



	public void setAll_count(int all_count) {
		this.all_count = all_count;
	}



	public int getCost_price() {
		return cost_price;
	}



	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}



	public int getSale_price() {
		return sale_price;
	}



	public void setSale_price(int sale_price) {
		this.sale_price = sale_price;
	}



	public int getProduct_count() {
		return product_count;
	}



	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}



	public String getProduct_kname() {
		return product_kname;
	}



	public void setProduct_kname(String product_kname) {
		this.product_kname = product_kname;
	}



	public String getProduct_ename() {
		return product_ename;
	}



	public void setProduct_ename(String product_ename) {
		this.product_ename = product_ename;
	}



	@Override
	public String toString() {
		return "Inventory [totalStock=" + totalStock + ", totalShop=" + totalShop + ", todayReceiving=" + todayReceiving
				+ ", inven_no=" + inven_no + ", inven_count=" + inven_count + ", inven_date=" + inven_date + ", status="
				+ status + ", product_no=" + product_no + ", inven_price=" + inven_price + ", all_count=" + all_count
				+ ", cost_price=" + cost_price + ", sale_price=" + sale_price + ", product_count=" + product_count
				+ ", product_kname=" + product_kname + ", product_ename=" + product_ename + "]";
	}
	


	

}
