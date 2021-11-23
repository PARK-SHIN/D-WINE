package com.project.dwine.manage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.manage.model.dao.MemberMgMapper;
import com.project.dwine.member.model.vo.Member;

@Service("MemberMgService")
public class MemberMgServiceImpl implements MemberMgService {

	private final MemberMgMapper memberMgMapper;
	
	@Autowired
	public MemberMgServiceImpl(MemberMgMapper memberMgMapper) {
		this.memberMgMapper = memberMgMapper;
	}
	
	@Override
	public List<Member> selectMemberMgList() {
		
		return memberMgMapper.selectMemberMgList();
	}

}
