package com.project.dwine.member.model.sevice;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.dwine.member.model.vo.Member;

public interface MemberService extends UserDetailsService{

	void join(Member member, String birth);

}
