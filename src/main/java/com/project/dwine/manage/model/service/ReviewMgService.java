package com.project.dwine.manage.model.service;

import java.util.List;

import com.project.dwine.manage.model.vo.Report;
import com.project.dwine.manage.model.vo.Review;

public interface ReviewMgService {

	List<Review> selectReviewList();

	void deleteReviewMg(String review_no);

	


}
