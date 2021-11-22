package com.project.dwine.cart.model.vo;

public class Cart {

	private int cart_no;
	private int cart_count;
	private int user_no;
	private int product_no;
	
	public Cart() {}

	public Cart(int cart_no, int cart_count, int user_no, int product_no) {
		super();
		this.cart_no = cart_no;
		this.cart_count = cart_count;
		this.user_no = user_no;
		this.product_no = product_no;
	}

	public int getCart_no() {
		return cart_no;
	}

	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}

	public int getCart_count() {
		return cart_count;
	}

	public void setCart_count(int cart_count) {
		this.cart_count = cart_count;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	@Override
	public String toString() {
		return "Cart [cart_no=" + cart_no + ", cart_count=" + cart_count + ", user_no=" + user_no + ", product_no="
				+ product_no + "]";
	}
	
}
