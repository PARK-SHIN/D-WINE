package com.project.dwine.recommendWine.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.hashtag.model.vo.Hashtag;
import com.project.dwine.product.model.vo.Product;
import com.project.dwine.recommendWine.model.dao.RecommendMapper;

@Service
public class RecommendServiceImpl implements RecommendService{
	
	private final RecommendMapper recommendMapper;
	
	@Autowired
	public RecommendServiceImpl(RecommendMapper recommendMapper) {
		this.recommendMapper = recommendMapper;
	}

	@Override
	public List<Hashtag> selectHashtagList(int hashType) {
		return recommendMapper.selectHashtagList(hashType);
	}

	@Override
	public List<Product> selectWineList(int hashNo) {
		return recommendMapper.selectWineList(hashNo);
	}

	@Override
	public List<Product> searchWineList(String hashName) {
		return recommendMapper.searchWineList(hashName);
	}

}
