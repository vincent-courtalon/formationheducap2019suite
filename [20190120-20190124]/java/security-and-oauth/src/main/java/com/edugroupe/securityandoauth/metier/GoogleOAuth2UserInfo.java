package com.edugroupe.securityandoauth.metier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GoogleOAuth2UserInfo {
	private String id;
	private String name;
	private String email;
	private String imageUrl;

}
