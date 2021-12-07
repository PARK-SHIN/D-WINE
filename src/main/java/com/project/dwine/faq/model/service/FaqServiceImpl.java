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
	public int getFaqCount(String searchValue) {
		return faqMapper.getFaqCount(searchValue);
	}

	@Override
	public List<Faq> selectFaqList(String searchValue, int startRow, int endRow) {
		return faqMapper.selectFaqList(searchValue, startRow, endRow);
	}

	@Override
	public int deleteFaq(int faqNo) {
		return faqMapper.deleteFaq(faqNo);
	}

	@Override
	public int registFaq(String title, String content, int userNo) {
		return faqMapper.registFaq(title, content, userNo);
	}

	@Override
	public Faq selectFaq(int faqNo) {
		return faqMapper.selectFaq(faqNo);
	}

	@Override
	public int updateFaq(String title, String content, int faqNo) {
		return faqMapper.updateFaq(title, content, faqNo);
	}

}
