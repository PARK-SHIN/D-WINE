package com.project.dwine.manage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.manage.model.dao.ReviewMgMapper;
import com.project.dwine.manage.model.vo.Report;


@Service("ReviewMgService")
public class ReviewMgServiceImpl implements ReviewMgService {

	private final ReviewMgMapper reviewMgMapper;
	
	@Autowired
	public ReviewMgServiceImpl(ReviewMgMapper reviewMgMapper) {
		this.reviewMgMapper = reviewMgMapper;
	}
	
	@Override
	public List<Report> selectReviewList() {
		return reviewMgMapper.selectReviewList();
	}

}
