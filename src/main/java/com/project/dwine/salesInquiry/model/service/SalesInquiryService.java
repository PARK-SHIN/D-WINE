package com.project.dwine.salesInquiry.model.service;

import java.util.List;
import java.util.Map;

import com.project.dwine.salesInquiry.model.vo.Total;

public interface SalesInquiryService {

	Total selectTodayStatus();
	
	List<Total> selectYear();

	List<Total> yearSales();

	int getDailyCount(Map<String, Object> daily);

	List<Total> searchDailyList(Map<String, Object> daily);

	List<Total> salesProduct();

	List<Total> changeYearSales(String year);

}
