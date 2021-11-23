package com.project.dwine.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.notice.model.vo.Notice;

@Mapper
public interface NoticeMapper {
	
	List<Notice> selectNoticeList();

	Notice selectNoticeByno(int notice_no);
	
	int registNewNotice(Notice notice);

	int modifyNotice(Notice notice);

	void deleteNotice(String notice_no);


}
