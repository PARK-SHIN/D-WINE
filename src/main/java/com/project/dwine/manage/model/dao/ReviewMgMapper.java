package com.project.dwine.manage.model.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;


import com.project.dwine.manage.model.vo.Review;

@Mapper
public interface ReviewMgMapper {


	List<Review> selectReviewList();

	void deleteReviewMg(String user_no);

	Review selectOneReview(int review_no);

	

}
