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

	@Override
	public int hashNameCheck(String hashName) {
		return hashtagMapper.hashNameCheck(hashName);
	}

	@Override
	public int registHashtag(String hashName, int hashType) {
		return hashtagMapper.registHashtag(hashName, hashType);
	}

	@Override
	public Hashtag selectByHashNo(int hashNo) {
		return hashtagMapper.selectByHashNo(hashNo);
	}

	@Override
	public int modifyHashtag(int hashNo, String hashName, int hashType) {
		return hashtagMapper.modifyHashtag(hashNo, hashName, hashType);
	}

	@Override
	public int deleteHashtag(int hashNo) {
		return hashtagMapper.deleteHashtag(hashNo);
	}
}
