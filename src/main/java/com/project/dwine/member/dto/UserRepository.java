package com.project.dwine.member.dto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<tempUser, Long> {
	Optional<tempUser> findByEmail(String email); // 이미 email을 통해 생성된 사용자인지 체크
}
