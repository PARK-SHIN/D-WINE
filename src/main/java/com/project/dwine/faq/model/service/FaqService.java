package com.project.dwine.faq.model.service;

import java.util.List;

import com.project.dwine.faq.model.vo.Faq;

public interface FaqService {

	int getFaqCount(String searchValue);

	List<Faq> selectFaqList(String searchValue, int startRow, int endRow);

	int deleteFaq(int faqNo);

	int registFaq(String title, String content, int userNo);

	Faq selectFaq(int faqNo);

	int updateFaq(String title, String content, int faqNo);

}
