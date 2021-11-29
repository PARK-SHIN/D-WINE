package com.project.dwine.faq.model.service;

import java.util.List;

import com.project.dwine.faq.model.vo.Faq;

public interface FaqService {

	List<Faq> selectFaqList();

	int deleteFaq(int faqNo);

	int registFaq(String title, String content, int userNo);

	List<Faq> selectSearchList(String searchValue);

	Faq selectFaq(int faqNo);

	int updateFaq(String title, String content, int faqNo);

}
