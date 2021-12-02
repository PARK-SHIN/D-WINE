package com.project.dwine.member.model.sevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dwine.member.model.dao.MemberMapper;
import com.project.dwine.member.model.vo.Authority;
import com.project.dwine.member.model.vo.Member;
import com.project.dwine.member.model.vo.UserImpl;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberMapper memberMapper;

	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println(username);
		Member member = memberMapper.findMemberById(username);

		if (member == null) {
			member = new Member();
		}
		List<GrantedAuthority> authorities = new ArrayList<>();

		if (member.getAuthority() != null) {
			Authority authority = member.getAuthority();

			if (authorities != null) {
				authorities.add(new SimpleGrantedAuthority(authority.getName()));
			}

		}

		UserImpl user = new UserImpl(member.getUser_id(), member.getUser_pw(), authorities);
		user.setDetails(member);

		return user;
	}

	@Override
	public int checkId(String userEmail) {

		int result = memberMapper.checkId(userEmail);

		return result;
	}

	@Override
	public int join(Member member, String birth) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setUser_pw(passwordEncoder.encode(member.getUser_pw()));

		HashMap<String, Object> joinMember = new HashMap<>();
		joinMember.put("member", member);
		joinMember.put("birthday", birth);

		int result = memberMapper.joinMember(joinMember);

		return result;

	}

	@Override
	public int findMemberByNickname(String nickName) {

		int result = memberMapper.findMemberByNickname(nickName);

		return result;
	}

	@Override
	public String findMemberId(String user_name, String user_phone) {

		String userId = memberMapper.findMemberId(user_name, user_phone);

		return userId;
	}

	@Override
	public int findMemberByName_Id(String user_name, String user_id) {
		int findMemberByName_Id = memberMapper.findMemberByName_Id(user_name, user_id);
		return findMemberByName_Id;
	}

	@Override
	public void updateTempPassword(String user_id, String tempPassword) {
		memberMapper.updateTempPassword(user_id, tempPassword);

	}

	@Override
	public int checkUser(String user_name, String user_phone) {
		return memberMapper.checkUser(user_name, user_phone);
	}

}
