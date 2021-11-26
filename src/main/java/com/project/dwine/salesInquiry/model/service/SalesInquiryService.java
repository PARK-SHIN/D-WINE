package com.project.dwine.salesInquiry.model.service;

import java.util.List;

import com.project.dwine.salesInquiry.model.vo.Total;

public interface SalesInquiryService {

	Total selectTodayStatus();

	List<Total> dailyList();

}
