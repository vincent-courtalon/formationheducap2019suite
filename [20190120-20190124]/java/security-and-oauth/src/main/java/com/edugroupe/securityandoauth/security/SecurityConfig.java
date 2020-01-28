package com.edugroupe.securityandoauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthorizationRequestRepository customAuthorizationRequestRepository() {
		return new HttpSessionOAuth2AuthorizationRequestRepository();
	}
	
	@Autowired private OidcUserService oidcUserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and()
			.oauth2Login()
			// customisation de l'url pour l'autorisation Oauth
			.authorizationEndpoint().baseUri("/oauth2/authorize")
									.authorizationRequestRepository(
											customAuthorizationRequestRepository()).and()
			.redirectionEndpoint()
				.baseUri("/oauth2/callback/*") //url a laquelle google nous rapellera (tranmit avec notre requette d'authentification
				.and()
			.userInfoEndpoint()
				.oidcUserService(oidcUserService); // personnaliser l'extraction d'informations
	}

	
	
}
