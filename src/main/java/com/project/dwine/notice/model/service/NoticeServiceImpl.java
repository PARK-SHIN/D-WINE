package com.project.dwine.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.notice.model.dao.NoticeMapper;
import com.project.dwine.notice.model.vo.Notice;
import com.project.dwine.product.model.vo.Product;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeMapper noticeMapper;
	
	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@Override
	public List<Notice> selectNoticeList(int startRow, int endRow) {
		
		return noticeMapper.selectNoticeList(startRow, endRow);
	}
	
	@Override
	public Notice selectNoticeByNo(int notice_no) {
		return noticeMapper.selectNoticeByno(notice_no);
	}
	

	@Override
	public int registNewNotice(Notice notice) {
		
		return noticeMapper.registNewNotice(notice);
	}

	@Override
	public int modifyNotice(Notice notice) {
		
		return noticeMapper.modifyNotice(notice);
	}


	@Override
	public void deleteNotice(String notice_no) {
		noticeMapper.deleteNotice(notice_no);
		
	}

	@Override
	public List<Notice> searchNoticeList(String searchValue, int startRow, int endRow) {
		return noticeMapper.searchNoticeList(searchValue, startRow, endRow);
	}

	@Override
	public int noticeTotalListCnt() {
		return noticeMapper.noticeTotalListCnt();
	}

	@Override
	public int noticeSearchListCnt(String searchValue) {
		return noticeMapper.noticeSearchListCnt(searchValue);
	}

	
	


}
