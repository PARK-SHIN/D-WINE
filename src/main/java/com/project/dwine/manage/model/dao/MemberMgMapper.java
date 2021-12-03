package com.project.dwine.manage.model.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.project.dwine.member.model.vo.Member;
import com.project.dwine.notice.model.vo.Notice;

@Mapper
public interface MemberMgMapper {

	List<Member> selectMemberMgList(int startRow, int endRow);
	List<Member> searchMemberList(String searchValue, int startRow, int endRow);

	Member selectMemberMgByNo(int user_no);

	void deleteMemberMg(String user_no);

	int memberMgTotalListCnt();

	int searchMemberListCnt(String searchValue);


}
