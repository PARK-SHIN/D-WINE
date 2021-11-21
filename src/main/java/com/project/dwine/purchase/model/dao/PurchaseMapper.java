package com.project.dwine.purchase.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.purchase.model.vo.Hashtag;
import com.project.dwine.purchase.model.vo.Product;

@Mapper
public interface PurchaseMapper {

	List<Product> wineList();

	Product wineDetail(String id);

	List<Hashtag> hashList();

	List<Product> filterWineList(String type, String price, String country, String variety, String name);

	List<Product> sortWineList(String val);

}
