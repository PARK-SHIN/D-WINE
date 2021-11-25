package com.project.dwine.purchase.model.service;

import java.util.List;

import com.project.dwine.purchase.model.vo.Hashtag;
import com.project.dwine.purchase.model.vo.Product;
import com.project.dwine.wish.model.vo.Wish;

public interface PurchaseService {

	List<Product> wineList();

	Product wineDetail(String id);

	List<Hashtag> hashtag(String id);

	List<Product> filterWineList(String type, String price, String country, String variety, String name);

	List<Product> sortWineList(String val);

	Wish checkWish(int user_no, int product_no);

}
