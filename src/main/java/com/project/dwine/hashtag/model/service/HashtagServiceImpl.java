package com.project.dwine.hashtag.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.hashtag.model.dao.HashtagMapper;
import com.project.dwine.hashtag.model.vo.Hashtag;

@Service
public class HashtagServiceImpl implements HashtagService{

	private final HashtagMapper hashtagMapper;
	
	// 생성자 주입
	@Autowired
	public HashtagServiceImpl(HashtagMapper hashtagMapper) {
		this.hashtagMapper = hashtagMapper;
	}

	@Override
	public List<Hashtag> selectHashtagList(int hashType) {
		return hashtagMapper.selectHashtagList(hashType);
	}
}
