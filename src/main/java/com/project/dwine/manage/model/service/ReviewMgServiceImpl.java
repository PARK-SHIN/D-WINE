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
	public List<Review> selectReviewList(int startRow, int endRow) {
		return reviewMgMapper.selectReviewList(startRow, endRow);
	}
	
	@Override
	public Review selectReviewList(int review_no) {
		return reviewMgMapper.selectReviewList(review_no);
	}
	
	@Override
	public Review selectOneReview(int review_no) {
		return reviewMgMapper.selectOneReview(review_no);
	}


	@Override
	public void deleteReviewMg(String review_no) {
		reviewMgMapper.deleteReviewMg(review_no);
		
	}


	@Override
	public int reviewMgTotalListCnt() {
		return reviewMgMapper.reviewMgTotalListCnt();
	}




	
	

}
