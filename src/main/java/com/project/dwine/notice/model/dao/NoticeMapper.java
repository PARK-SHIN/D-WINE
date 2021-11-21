package com.project.dwine.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.notice.model.vo.Notice;

@Mapper
public interface NoticeMapper {
	
	List<Notice> selectNoticeList();
	
	int registNewNotice(Notice newNotice);

}
