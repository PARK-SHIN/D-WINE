package com.project.dwine.recommendWine.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.hashtag.model.vo.Hashtag;
import com.project.dwine.product.model.vo.Product;

@Mapper
public interface RecommendMapper {

	List<Hashtag> selectHashtagList(int hashType);

	List<Product> selectWineList(int hashNo);

	List<Product> searchWineList(String hashName);

	List<Product> selectProductByWeather(int typeNo, int varietyNo1, int varietyNo2);

}
