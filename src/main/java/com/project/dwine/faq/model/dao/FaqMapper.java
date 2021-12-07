package com.project.dwine.faq.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.faq.model.vo.Faq;

@Mapper
public interface FaqMapper {

	int getFaqCount(String searchValue);

	List<Faq> selectFaqList(String searchValue, int startRow, int endRow);

	int deleteFaq(int faqNo);

	int registFaq(String title, String content, int userNo);

	Faq selectFaq(int faqNo);

	int updateFaq(String title, String content, int faqNo);

}
