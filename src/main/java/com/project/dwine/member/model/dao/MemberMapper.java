package com.project.dwine.member.model.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	void joinMember(HashMap<String, Object> joinMember);

	Member findMemberById(String userId);

}
