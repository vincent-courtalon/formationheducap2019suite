package com.edugroupe.mybooksform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

	private PasswordEncoder passwordEncoder;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		if (passwordEncoder == null)
			passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
	
	@Autowired private CustomUserDetailsService userDetailsService;

	// cette méthode overridée détermine la configuration de l'authentifications
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

	// cette méthode configure la sécurité au niveau des urls
	// autoriser ou pas l'accès a des urls, configurer certains aspects (cookie, protection csrf, etc...)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/api", "/api/**", "/lecteurs", "/lecteurs/**").authenticated().and()
//				.antMatchers("/**").denyAll().and()
			.httpBasic().and()
			.csrf().disable(); // desactive la protection cross site request forgery
								// cette protection requiert un token pour les requetes post, put, etc...
	}
	
	
	
	
}
