package com.project.dwine.member.model.sevice;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.dwine.member.model.vo.Member;

public interface MemberService extends UserDetailsService {

	int join(Member member, String birth);

	int findMemberByNickname(String nickName);

	int checkId(String userEmail);

}
