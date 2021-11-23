package com.project.dwine.member.dto;

import java.util.Map;

public class OAuthAttributes {
	private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
	private String nameAttributeKey;
	private String name;
	private String email;
	private String age;

	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
			String age) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public String getNameAttributeKey() {
		return nameAttributeKey;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAge() {
		return age;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public void setNameAttributeKey(String nameAttributeKey) {
		this.nameAttributeKey = nameAttributeKey;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		return ofKakao("id", attributes);
	}

	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		// kakao는 kakao_account에 유저정보가 있다. (email)
		Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
		// kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
		Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

		return new OAuthAttributes(attributes, userNameAttributeName, (String) kakaoProfile.get("nickname"),
				(String) kakaoAccount.get("email"), (String) kakaoAccount.get("age_range"));

	}

	public tempUser toEntity() {
		return new tempUser(name, email, age, "ROLE_GUEST");
	}

}
