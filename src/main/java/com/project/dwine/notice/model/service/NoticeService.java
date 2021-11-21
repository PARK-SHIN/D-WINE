package com.project.dwine.notice.model.service;

import java.util.List;

import com.project.dwine.notice.model.vo.Notice;

public interface NoticeService {
	
	List<Notice> selectNoticeList();
	
	
	
	int registNewNotice(Notice newNotice);

}
