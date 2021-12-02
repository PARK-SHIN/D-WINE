package com.project.dwine.manage.model.service;

import java.util.List;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.notice.model.vo.Notice;

public interface MemberMgService {

	List<Member> selectMemberMgList(int startRow, int endRow);

	Member selectMemberMgByNo(int user_no);

	void deleteMemberMg(String user_no);

	int memberMgTotalListCnt();

	int searchMemberListCnt(String searchValue);

	List<Member> searchMemberList(String searchValue, int startRow, int endRow);

}
