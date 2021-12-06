package com.project.dwine.salesInquiry.model.service;

import java.util.List;
import java.util.Map;

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
	public List<Total> selectYear() {
		return salesInquiryMapper.selectYear();
	}

	@Override
	public List<Total> yearSales() {
		return salesInquiryMapper.yearSales();
	}

	@Override
	public int getDailyCount(Map<String, Object> daily) {
		return salesInquiryMapper.getDailyCount(daily);
	}

	@Override
	public List<Total> searchDailyList(Map<String, Object> daily) {
		return salesInquiryMapper.searchDailyList(daily);
	}

	@Override
	public List<Total> salesProduct() {
		return salesInquiryMapper.salesProduct();
	}

	@Override
	public List<Total> changeYearSales(String year) {
		return salesInquiryMapper.changeYearSales(year);
	}



}
