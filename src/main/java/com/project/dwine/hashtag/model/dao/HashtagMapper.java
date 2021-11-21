package com.project.dwine.hashtag.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.hashtag.model.vo.Hashtag;

@Mapper
public interface HashtagMapper {

	List<Hashtag> selectHashtagList(int hashType);

	int hashNameCheck(String hashName);

	int registHashtag(String hashName, int hashType);

	Hashtag selectByHashNo(int hashNo);

	int modifyHashtag(int hashNo, String hashName, int hashType);

	int deleteHashtag(int hashNo);
}
