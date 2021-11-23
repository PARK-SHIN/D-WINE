package com.project.dwine.member.model.sevice;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.dwine.member.model.vo.Member;

public interface MemberService extends UserDetailsService {

	int join(Member member, String birth);

	int findMemberByNickname(String nickName);

	int checkId(String userEmail);

	String findMemberId(String user_name, String user_phone);

	int findMemberByName_Id(String user_name, String user_id);

	void updateTempPassword(String user_id, String tempPassword);

	int checkUser(String user_name, String user_phone);

}
