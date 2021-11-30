package com.project.dwine.purchase.model.vo;

import java.util.Date;
import java.util.List;

import com.project.dwine.cart.model.vo.Cart;

public class Product {

	private int product_no;
	private String product_kname;
	private String product_ename;
	private int cost_price;
	private int sale_price;
	private int product_count;
	private String winery;
	private String thumbnail;
	private int capacity;
	private int abv;
	private String taste_graph;
	private String information;
	private String award;
	private String tip;
	private String label_image;
	private String status;
	private Date create_date;
	private Date modify_date;
	private int variety_no;
	private int type_no;
	private int country_no;
	
	/* Join 해온 값*/
	private String country_name;
	private String type_name;
	private String variety_name;
	private List<Hashtag> hash_names;
	private List<Cart> cart;
	private List<OrderDetail> orderDetail;
	private List<Review> review;
	private int order_count;
	
	public Product() {}


	

	public Product(int product_no, String product_kname, String product_ename, int cost_price, int sale_price,
			int product_count, String winery, String thumbnail, int capacity, int abv, String taste_graph,
			String information, String award, String tip, String label_image, String status, Date create_date,
			Date modify_date, int variety_no, int type_no, int country_no, String country_name, String type_name,
			String variety_name, List<Hashtag> hash_names, List<Cart> cart, List<OrderDetail> orderDetail,
			List<Review> review, int order_count) {
		super();
		this.product_no = product_no;
		this.product_kname = product_kname;
		this.product_ename = product_ename;
		this.cost_price = cost_price;
		this.sale_price = sale_price;
		this.product_count = product_count;
		this.winery = winery;
		this.thumbnail = thumbnail;
		this.capacity = capacity;
		this.abv = abv;
		this.taste_graph = taste_graph;
		this.information = information;
		this.award = award;
		this.tip = tip;
		this.label_image = label_image;
		this.status = status;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.variety_no = variety_no;
		this.type_no = type_no;
		this.country_no = country_no;
		this.country_name = country_name;
		this.type_name = type_name;
		this.variety_name = variety_name;
		this.hash_names = hash_names;
		this.cart = cart;
		this.orderDetail = orderDetail;
		this.review = review;
		this.order_count = order_count;
	}




	public Product(int product_no, String product_kname, String product_ename, int cost_price, int sale_price,
			int product_count, String winery, String thumbnail, int capacity, int abv, String taste_graph,
			String information, String award, String tip, String label_image, String status, Date create_date,
			Date modify_date, int variety_no, int type_no, int country_no, String country_name, String type_name,
			String variety_name, List<Hashtag> hash_names, List<Cart> cart, List<OrderDetail> orderDetail) {
		super();
		this.product_no = product_no;
		this.product_kname = product_kname;
		this.product_ename = product_ename;
		this.cost_price = cost_price;
		this.sale_price = sale_price;
		this.product_count = product_count;
		this.winery = winery;
		this.thumbnail = thumbnail;
		this.capacity = capacity;
		this.abv = abv;
		this.taste_graph = taste_graph;
		this.information = information;
		this.award = award;
		this.tip = tip;
		this.label_image = label_image;
		this.status = status;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.variety_no = variety_no;
		this.type_no = type_no;
		this.country_no = country_no;
		this.country_name = country_name;
		this.type_name = type_name;
		this.variety_name = variety_name;
		this.hash_names = hash_names;
		this.cart = cart;
		this.orderDetail = orderDetail;
	}





	public Product(int product_no, String product_kname, String product_ename, int cost_price, int sale_price,
			int product_count, String winery, String thumbnail, int capacity, int abv, String taste_graph,
			String information, String award, String tip, String label_image, String status, Date create_date,
			Date modify_date, int variety_no, int type_no, int country_no) {
		super();
		this.product_no = product_no;
		this.product_kname = product_kname;
		this.product_ename = product_ename;
		this.cost_price = cost_price;
		this.sale_price = sale_price;
		this.product_count = product_count;
		this.winery = winery;
		this.thumbnail = thumbnail;
		this.capacity = capacity;
		this.abv = abv;
		this.taste_graph = taste_graph;
		this.information = information;
		this.award = award;
		this.tip = tip;
		this.label_image = label_image;
		this.status = status;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.variety_no = variety_no;
		this.type_no = type_no;
		this.country_no = country_no;
	}


	public Product(int product_no, String product_kname, String product_ename, int cost_price, int sale_price,
			int product_count, String winery, String thumbnail, int capacity, int abv, String taste_graph,
			String information, String award, String tip, String label_image, String status, Date create_date,
			Date modify_date, int variety_no, int type_no, int country_no, String country_name, String type_name,
			String variety_name, List<Hashtag> hash_names) {
		super();
		this.product_no = product_no;
		this.product_kname = product_kname;
		this.product_ename = product_ename;
		this.cost_price = cost_price;
		this.sale_price = sale_price;
		this.product_count = product_count;
		this.winery = winery;
		this.thumbnail = thumbnail;
		this.capacity = capacity;
		this.abv = abv;
		this.taste_graph = taste_graph;
		this.information = information;
		this.award = award;
		this.tip = tip;
		this.label_image = label_image;
		this.status = status;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.variety_no = variety_no;
		this.type_no = type_no;
		this.country_no = country_no;
		this.country_name = country_name;
		this.type_name = type_name;
		this.variety_name = variety_name;
		this.hash_names = hash_names;
	}

	

	public Product(int product_no, String product_kname, String product_ename, int sale_price, int product_count, String thumbnail,
			String status, List<Cart> cart) {
		super();
		this.product_no = product_no;
		this.product_kname = product_kname;
		this.product_ename = product_ename;
		this.sale_price = sale_price;
		this.product_count = product_count;
		this.thumbnail = thumbnail;
		this.status = status;
		this.cart = cart;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
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

	public String getWinery() {
		return winery;
	}

	public void setWinery(String winery) {
		this.winery = winery;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getAbv() {
		return abv;
	}

	public void setAbv(int abv) {
		this.abv = abv;
	}

	public String getTaste_graph() {
		return taste_graph;
	}

	public void setTaste_graph(String taste_graph) {
		this.taste_graph = taste_graph;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getLabel_image() {
		return label_image;
	}

	public void setLabel_image(String label_image) {
		this.label_image = label_image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public int getVariety_no() {
		return variety_no;
	}

	public void setVariety_no(int variety_no) {
		this.variety_no = variety_no;
	}

	public int getType_no() {
		return type_no;
	}

	public void setType_no(int type_no) {
		this.type_no = type_no;
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

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getVariety_name() {
		return variety_name;
	}

	public void setVariety_name(String variety_name) {
		this.variety_name = variety_name;
	}

	public List<Hashtag> getHash_names() {
		return hash_names;
	}

	public void setHash_names(List<Hashtag> hash_names) {
		this.hash_names = hash_names;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}


	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}


	public void setOrrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}


	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	

	public int getOrder_count() {
		return order_count;
	}




	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}




	@Override
	public String toString() {
		return "Product [product_no=" + product_no + ", product_kname=" + product_kname + ", product_ename="
				+ product_ename + ", cost_price=" + cost_price + ", sale_price=" + sale_price + ", product_count="
				+ product_count + ", winery=" + winery + ", thumbnail=" + thumbnail + ", capacity=" + capacity
				+ ", abv=" + abv + ", taste_graph=" + taste_graph + ", information=" + information + ", award=" + award
				+ ", tip=" + tip + ", label_image=" + label_image + ", status=" + status + ", create_date="
				+ create_date + ", modify_date=" + modify_date + ", variety_no=" + variety_no + ", type_no=" + type_no
				+ ", country_no=" + country_no + ", country_name=" + country_name + ", type_name=" + type_name
				+ ", variety_name=" + variety_name + ", hash_names=" + hash_names + ", cart=" + cart + ", orderDetail="
				+ orderDetail + ", review=" + review + ", order_count=" + order_count + "]";
	}




	
	
//	@Override
//	public String toString() {
//		return "Product [product_no=" + product_no + ", product_kname=" + product_kname + ", product_ename="
//				+ product_ename + ", cost_price=" + cost_price + ", sale_price=" + sale_price + ", product_count="
//				+ product_count + ", winery=" + winery + ", thumbnail=" + thumbnail + ", capacity=" + capacity
//				+ ", abv=" + abv + ", taste_graph=" + taste_graph + ", information=" + information + ", award=" + award
//				+ ", tip=" + tip + ", label_image=" + label_image + ", status=" + status + ", create_date="
//				+ create_date + ", modify_date=" + modify_date + ", variety_no=" + variety_no + ", type_no=" + type_no
//				+ ", country_no=" + country_no + ", country_name=" + country_name + ", type_name=" + type_name
//				+ ", variety_name=" + variety_name + ", hash_names=" + hash_names + "]";
//	}


//
//	@Override
//	public String toString() {
//		return "Product [product_no=" + product_no + ", product_kname=" + product_kname + ", product_ename="
//				+ product_ename + ", cost_price=" + cost_price + ", sale_price=" + sale_price + ", product_count="
//				+ product_count + ", winery=" + winery + ", thumbnail=" + thumbnail + ", capacity=" + capacity
//				+ ", abv=" + abv + ", taste_graph=" + taste_graph + ", information=" + information + ", award=" + award
//				+ ", tip=" + tip + ", label_image=" + label_image + ", status=" + status + ", create_date="
//				+ create_date + ", modify_date=" + modify_date + ", variety_no=" + variety_no + ", type_no=" + type_no
//				+ ", country_no=" + country_no + "]";
//	}

	
	
	
	
}
