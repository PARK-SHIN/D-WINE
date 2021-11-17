package com.project.dwine.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.notice.model.dao.NoticeMapper;
import com.project.dwine.notice.model.vo.Notice;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeMapper noticeMapper;
	
	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@Override
	public List<Notice> selectNoticeList() {
		
		return noticeMapper.selectNoticeList();
	}

	@Override
	public int registNewNotice(Notice newNotice) {
		
		return 0;
	}

}
