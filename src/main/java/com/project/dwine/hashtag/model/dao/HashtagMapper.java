package com.project.dwine.hashtag.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.hashtag.model.vo.Hashtag;

@Mapper
public interface HashtagMapper {

	List<Hashtag> selectHashtagList(int hashType);
}
