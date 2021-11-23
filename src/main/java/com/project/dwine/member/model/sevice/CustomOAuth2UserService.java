package com.project.dwine.member.model.sevice;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.project.dwine.member.dto.CustomOath2User;
import com.project.dwine.member.dto.OAuthAttributes;
import com.project.dwine.member.dto.SessionUser;
import com.project.dwine.member.dto.UserRepository;
import com.project.dwine.member.dto.tempUser;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final UserRepository userRepository;
	private final HttpSession httpSession;

	@Autowired
	public CustomOAuth2UserService(UserRepository userRepository, HttpSession httpSession) {
		this.userRepository = userRepository;
		this.httpSession = httpSession;
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		// OAuth2 서비스 id (구글, 카카오, 네이버)
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		// OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
				.getUserNameAttributeName();

		// OAuth2UserService
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
				oAuth2User.getAttributes());

		if (attributes.getAge() == null) {
			attributes.setAge("disagreeAge");
		}
		if (attributes.getEmail() == null) {
			attributes.setEmail("disagreeEmail");
		}
		tempUser user = saveOrUpdate(attributes);

		httpSession.setAttribute("user", new SessionUser(user)); // SessionUser (직렬화된 dto 클래스 사용)
		httpSession.setAttribute("accessToken", userRequest.getAccessToken().getTokenValue());
		CustomOath2User customOath2User = new CustomOath2User(
				Collections.singleton(new SimpleGrantedAuthority(user.getRole())), attributes.getAttributes(),
				attributes.getNameAttributeKey());
		customOath2User.setNickname(attributes.getName());
		customOath2User.setAge(attributes.getAge());

		return customOath2User;
	}

	// 유저 생성 및 수정 서비스 로직
	private tempUser saveOrUpdate(OAuthAttributes attributes) {
		tempUser user = userRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getName(), attributes.getAge())).orElse(attributes.toEntity());
		return userRepository.save(user);
	}

}
