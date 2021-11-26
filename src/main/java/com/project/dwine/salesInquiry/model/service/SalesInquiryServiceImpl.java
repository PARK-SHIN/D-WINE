package com.project.dwine.salesInquiry.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.salesInquiry.model.dao.SalesInquiryMapper;
import com.project.dwine.salesInquiry.model.vo.Total;

@Service("salesInquiryService")
public class SalesInquiryServiceImpl implements SalesInquiryService{
	
	private final SalesInquiryMapper salesInquiryMapper;
	
	@Autowired
	public SalesInquiryServiceImpl(SalesInquiryMapper salesInquiryMapper) {
		this.salesInquiryMapper = salesInquiryMapper;
	}

	@Override
	public Total selectTodayStatus() {
		return salesInquiryMapper.selectTodayStatus();
	}

	@Override
	public List<Total> dailyList() {
		return salesInquiryMapper.dailyList();
	}

}
