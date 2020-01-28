package com.edugroupe.myldapsecurityform.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${myldapserver.url}")
	private String myLdapServerUrl;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()
			.contextSource().url(myLdapServerUrl)
							.managerDn("cn=admin,dc=example,dc=org")
							.managerPassword("admin")
							.and()
			.userDnPatterns("uid={0},ou=peoples")
			.groupSearchBase("ou=groups")
			.groupRoleAttribute("cn")
			.groupSearchFilter("uniqueMember={0}");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated()
			.and().httpBasic();
	}

	
}
