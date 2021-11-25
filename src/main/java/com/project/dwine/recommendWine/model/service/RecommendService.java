package com.project.dwine.recommendWine.model.service;

import java.util.List;

import com.project.dwine.hashtag.model.vo.Hashtag;

public interface RecommendService {

	List<Hashtag> selectHashtagList(int hashType);

}
