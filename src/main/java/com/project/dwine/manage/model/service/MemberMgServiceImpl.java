package com.project.dwine.manage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.manage.model.dao.MemberMgMapper;
import com.project.dwine.member.model.vo.Member;
import com.project.dwine.notice.model.vo.Notice;

@Service("MemberMgService")
public class MemberMgServiceImpl implements MemberMgService {

	private final MemberMgMapper memberMgMapper;
	
	@Autowired
	public MemberMgServiceImpl(MemberMgMapper memberMgMapper) {
		this.memberMgMapper = memberMgMapper;
	}
	
	@Override
	public List<Member> selectMemberMgList(int startRow, int endRow) {
		
		return memberMgMapper.selectMemberMgList(startRow, endRow);
	}

	@Override
	public Member selectMemberMgByNo(int user_no) {
		return memberMgMapper.selectMemberMgByNo(user_no);
	}

	@Override
	public void deleteMemberMg(String user_no) {
		memberMgMapper.deleteMemberMg(user_no);

	}

	@Override
	public int memberMgTotalListCnt() {
		return memberMgMapper.memberMgTotalListCnt();
	}

	@Override
	public int searchMemberListCnt(String searchValue) {
		return memberMgMapper.searchMemberListCnt(searchValue);
	}

	@Override
	public List<Member> searchMemberList(String searchValue, int startRow, int endRow) {
		return memberMgMapper.searchMemberList(searchValue, startRow, endRow);
	}

}
