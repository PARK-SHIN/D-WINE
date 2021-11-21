package com.project.dwine.hashtag.model.service;

import java.util.List;

import com.project.dwine.hashtag.model.vo.Hashtag;

public interface HashtagService {

	List<Hashtag> selectHashtagList(int hashType);

	int hashNameCheck(String hashName);

	int registHashtag(String hashName, int hashType);

	Hashtag selectByHashNo(int hashNo);

	int modifyHashtag(int hashNo, String hashName, int hashType);

	int deleteHashtag(int hashNo);
}
