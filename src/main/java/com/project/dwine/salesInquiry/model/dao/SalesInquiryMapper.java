package com.project.dwine.salesInquiry.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.salesInquiry.model.vo.Total;


@Mapper
public interface SalesInquiryMapper {

	Total selectTodayStatus();

	List<Total> yearSales();

	int getDailyCount(Map<String, Object> daily);

	List<Total> searchDailyList(Map<String, Object> daily);

}
