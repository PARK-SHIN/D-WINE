package com.project.dwine.faq.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.faq.model.dao.FaqMapper;
import com.project.dwine.faq.model.vo.Faq;

@Service("faqService")
public class FaqServiceImpl implements FaqService{

	private final FaqMapper faqMapper;
	
	@Autowired
	public FaqServiceImpl(FaqMapper faqMapper) {
		this.faqMapper = faqMapper;
	}

	@Override
	public List<Faq> selectFaqList() {
		return faqMapper.selectFaqList();
	}

	@Override
	public int deleteFaq(int faqNo) {
		return faqMapper.deleteFaq(faqNo);
	}
}
