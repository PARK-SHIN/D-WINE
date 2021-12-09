package com.project.dwine.manage.model.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;


import com.project.dwine.manage.model.vo.Review;

@Mapper
public interface ReviewMgMapper {


	List<Review> selectReviewList(int startRow, int endRow);

	void deleteReviewMg(String user_no);

	List<Review> selectOneReview(int review_no);

	int reviewMgTotalListCnt();

	Review selectReviewList(int review_no);

	

}
