package com.project.dwine.salesInquiry.model.service;

import java.util.List;

import com.project.dwine.salesInquiry.model.vo.Total;

public interface SalesInquiryService {

	Total selectTodayStatus();

	List<Total> yearSales();

	List<Total> dailyList(int startRow, int endRow);

	int getDailyListCount();

	List<Total> searchDateList(int startRow, int endRow, String startDate, String endDate);

	int getDateListCount(String startDate, String endDate);


}
