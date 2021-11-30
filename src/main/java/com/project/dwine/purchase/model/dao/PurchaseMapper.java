package com.project.dwine.purchase.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.purchase.model.vo.Hashtag;
import com.project.dwine.purchase.model.vo.OrderDetail;
import com.project.dwine.purchase.model.vo.Payment;
import com.project.dwine.purchase.model.vo.Point;
import com.project.dwine.purchase.model.vo.Product;
import com.project.dwine.purchase.model.vo.Purchase;
import com.project.dwine.purchase.model.vo.Review;
import com.project.dwine.wish.model.vo.Wish;

@Mapper
public interface PurchaseMapper {

	List<Product> wineList();

	Product wineDetail(String id);

	List<Hashtag> hashList();

	List<Product> filterWineList(String type, String price, String country, String variety, String name);

	List<Product> sortWineList(String val);
	
	List<Product> popularList(String value);

	Wish checkWish(int user_no, int product_no);

	int purchaseInsert(Purchase purchase);

	int pointUpdate(int user_no, int user_point);

	int pointPlus(int user_no, int point, String purchase_no);

	int orderDetailInsert(OrderDetail orderDetail);

	int paymentInsert(Payment payment);

	int cartDelete(int user_no, List<Integer> cart_no);

	int stockUpdate(int product_no, int product_count);

	Member memberinfo(int user_no);

	Purchase selectPurchase(String purchase_no);

	List<OrderDetail> selectOrderDetail(String purchase_no);

	Point selectPoint(String purchase_no);

	List<Review> reviewList(String id);



}
