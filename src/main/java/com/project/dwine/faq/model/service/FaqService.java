package com.project.dwine.faq.model.service;

import java.util.List;

import com.project.dwine.faq.model.vo.Faq;

public interface FaqService {

	List<Faq> selectFaqList();

	int deleteFaq(int faqNo);

}
