package com.project.dwine.manage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.manage.model.dao.ReviewMgMapper;
import com.project.dwine.manage.model.vo.Review;


@Service("ReviewMgService")
public class ReviewMgServiceImpl implements ReviewMgService {

	private final ReviewMgMapper reviewMgMapper;
	
	@Autowired
	public ReviewMgServiceImpl(ReviewMgMapper reviewMgMapper) {
		this.reviewMgMapper = reviewMgMapper;
	}
	
	
	@Override
	public List<Review> selectReviewList() {
		return reviewMgMapper.selectReviewList();
	}
	

	@Override
	public void deleteReviewMg(String review_no) {
		reviewMgMapper.deleteReviewMg(review_no);
		
	}

	

}
