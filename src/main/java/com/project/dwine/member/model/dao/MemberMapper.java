package com.project.dwine.member.model.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	int joinMember(HashMap<String, Object> joinMember);

	Member findMemberById(String userId);

	int findMemberByNickname(String nickName);

	int checkId(String userEmail);

	String findMemberId(String user_name, String user_phone);

	int findMemberByName_Id(String user_name, String user_id);

	void updateTempPassword(String user_id, String user_pw);

	int checkUser(String user_name, String user_phone);

}
