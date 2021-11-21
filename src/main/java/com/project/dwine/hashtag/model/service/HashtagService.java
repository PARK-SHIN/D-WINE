package com.project.dwine.hashtag.model.service;

import java.util.List;

import com.project.dwine.hashtag.model.vo.Hashtag;

public interface HashtagService {

	List<Hashtag> selectHashtagList(int hashType);

	/*
	 * List<Hashtag> selectMoodList();
	 * 
	 * List<Hashtag> selectFoodList();
	 */

}
