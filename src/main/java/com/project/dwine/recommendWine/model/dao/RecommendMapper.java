package com.project.dwine.recommendWine.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.hashtag.model.vo.Hashtag;

@Mapper
public interface RecommendMapper {

	List<Hashtag> selectHashtagList(int hashType);

}
