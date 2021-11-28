package com.project.dwine.product.model.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.project.dwine.hashtag.model.vo.Hashtag;


public class Product{
	// 총 22 필드
	private int productNo;			// 상품 번호 
	private String productKName;	// 한글명
	private String productEName;	// 영문명
	private int salePrice;			// 판매가
	private int productCount;		// 수량
	private String winery;			// 와이너리
	private String thumbnail;		// 대표이미지
	private int capacity;			// 용량
	private double abv;				// 도수
	private String tasteGraph;		// 맛 그래프
	private String information;		// 상품 정보
	private String award;			// 상품 입상경력
	private String tip;				// 상품 팁
	private String labelImage;		// 라벨 이미지
	private String status;			// 상품 상태
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;		// 상품 등록일
	private Date modifyDate;		// 상품 수정일
	private Variety variety;		// 포도 품종
	private Type type;				// 와인 종류
	private Country country;		// 국가
	
	/* 한 상품은 여러 해시태그를 가질 수 있음 - PRODUCT_HASHTAG와 조인한 결과 값 */
	private List<Hashtag> HashtagList;
	
	public Product() {}

	public Product(int productNo, String productKName, String productEName, int salePrice,
			int productCount, String winery, String thumbnail, int capacity, double abv, String tasteGraph,
			String information, String award, String tip, String labelImage, String status, Date createDate,
			Date modifyDate, Variety variety, Type type, Country country, List<Hashtag> hashtagList) {
		super();
		this.productNo = productNo;
		this.productKName = productKName;
		this.productEName = productEName;
		this.salePrice = salePrice;
		this.productCount = productCount;
		this.winery = winery;
		this.thumbnail = thumbnail;
		this.capacity = capacity;
		this.abv = abv;
		this.tasteGraph = tasteGraph;
		this.information = information;
		this.award = award;
		this.tip = tip;
		this.labelImage = labelImage;
		this.status = status;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.variety = variety;
		this.type = type;
		this.country = country;
		this.HashtagList = hashtagList;
	}

	public Product(String productKName, String productEName, int salePrice, int productCount,
			String winery, String thumbnail, int capacity, double abv, String tasteGraph, String information,
			String award, String tip, String labelImage, Variety variety, Type type, Country country) {
		super();
		this.productKName = productKName;
		this.productEName = productEName;
		this.salePrice = salePrice;
		this.productCount = productCount;
		this.winery = winery;
		this.thumbnail = thumbnail;
		this.capacity = capacity;
		this.abv = abv;
		this.tasteGraph = tasteGraph;
		this.information = information;
		this.award = award;
		this.tip = tip;
		this.labelImage = labelImage;
		this.variety = variety;
		this.type = type;
		this.country = country;
	}
	
	public Product(int productNo, String productKName, String productEName, int salePrice,
			int productCount, String winery, String thumbnail, int capacity, double abv, String tasteGraph,
			String information, String award, String tip, String labelImage, Variety variety, Type type, Country country) {
		super();
		this.productNo = productNo;
		this.productKName = productKName;
		this.productEName = productEName;
		this.salePrice = salePrice;
		this.productCount = productCount;
		this.winery = winery;
		this.thumbnail = thumbnail;
		this.capacity = capacity;
		this.abv = abv;
		this.tasteGraph = tasteGraph;
		this.information = information;
		this.award = award;
		this.tip = tip;
		this.labelImage = labelImage;
		this.variety = variety;
		this.type = type;
		this.country = country;
	}

	public Product(String thumbnail, String labelImage) {
		super();
		this.thumbnail = thumbnail;
		this.labelImage = labelImage;
	}

	public int getProductNo() {
		return productNo;
	}

	public String getProductKName() {
		return productKName;
	}

	public String getProductEName() {
		return productEName;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public int getProductCount() {
		return productCount;
	}

	public String getWinery() {
		return winery;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public int getCapacity() {
		return capacity;
	}

	public double getAbv() {
		return abv;
	}

	public String getTasteGraph() {
		return tasteGraph;
	}

	public String getInformation() {
		return information;
	}

	public String getAward() {
		return award;
	}

	public String getTip() {
		return tip;
	}

	public String getLabelImage() {
		return labelImage;
	}

	public String getStatus() {
		return status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public Variety getVariety() {
		return variety;
	}

	public Type getType() {
		return type;
	}

	public Country getCountry() {
		return country;
	}

	public List<Hashtag> getHashtagList() {
		return HashtagList;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public void setProductKName(String productKName) {
		this.productKName = productKName;
	}

	public void setProductEName(String productEName) {
		this.productEName = productEName;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public void setWinery(String winery) {
		this.winery = winery;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setAbv(double abv) {
		this.abv = abv;
	}

	public void setTasteGraph(String tasteGraph) {
		this.tasteGraph = tasteGraph;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public void setLabelImage(String labelImage) {
		this.labelImage = labelImage;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setVariety(Variety variety) {
		this.variety = variety;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setHashtagList(List<Hashtag> hashtagList) {
		HashtagList = hashtagList;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productKName=" + productKName + ", productEName=" + productEName
				+ ", salePrice=" + salePrice + ", productCount=" + productCount
				+ ", winery=" + winery + ", thumbnail=" + thumbnail + ", capacity=" + capacity + ", abv=" + abv
				+ ", tasteGraph=" + tasteGraph + ", information=" + information + ", award=" + award + ", tip=" + tip
				+ ", labelImage=" + labelImage + ", status=" + status + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", variety=" + variety + ", type=" + type + ", country=" + country + ", HashtagList="
				+ HashtagList + "]";
	}

	

	
	
	

	
}	
