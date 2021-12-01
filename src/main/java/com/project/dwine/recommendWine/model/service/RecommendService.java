package com.project.dwine.recommendWine.model.service;

import java.util.List;

import com.project.dwine.hashtag.model.vo.Hashtag;
import com.project.dwine.product.model.vo.Product;

public interface RecommendService {

	List<Hashtag> selectHashtagList(int hashType);

	List<Product> selectWineList(int hashNo);

	List<Product> searchWineList(String hashName);

	List<Product> selectProductByWeather(int typeNo, int varietyNo1, int varietyNo2);

}
