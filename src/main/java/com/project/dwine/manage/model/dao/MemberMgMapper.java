package com.project.dwine.manage.model.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.project.dwine.member.model.vo.Member;

@Mapper
public interface MemberMgMapper {

	List<Member> selectMemberMgList();

}