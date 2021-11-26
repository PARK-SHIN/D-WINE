package com.project.dwine.salesInquiry.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.salesInquiry.model.vo.Total;


@Mapper
public interface SalesInquiryMapper {

	Total selectTodayStatus();

	List<Total> dailyList();
}
