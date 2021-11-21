package com.project.dwine.notice.model.service;

import java.util.List;

import com.project.dwine.notice.model.vo.Notice;

public interface NoticeService {
	
	List<Notice> selectNoticeList();
	
	Notice selectNoticeByNo(int notice_no);

	int registNewNotice(int notice_category, String notice_title, String notice_context, int user_no);



	

}
