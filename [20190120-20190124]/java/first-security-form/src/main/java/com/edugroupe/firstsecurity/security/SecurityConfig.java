package com.edugroupe.firstsecurity.security;

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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


// jsr250Enabled -> @RoleAllowed possible
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private PasswordEncoder passwordEncoder;
	// cet encoder n'encode rien
	// spring security depuis spring 5, requiert obligatoirement
	// un password encoder
	// celui-ci est censé encoder les mot de passes (typiquement par hashage)
	// ici, on ne vaut pas hasher les mots de passe
	// donc on utilise un encoder spécial qui ne fait rien
	// le nooppasswordencoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		if (passwordEncoder == null)
			passwordEncoder = new BCryptPasswordEncoder();
			//passwordEncoder = NoOpPasswordEncoder.getInstance();
		return passwordEncoder;
	}
	
	// recuperation utilisateur par login
	@Autowired private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// authentification en memoire, les comptes utilisateurs sont défini directement ici
		// et n'existe que dans la mémoire après
		// utile dans des cas simples ou pour tester
		/*auth.inMemoryAuthentication()
				.withUser("admin").password("admin").roles("ADMIN", "USER").and()
				.withUser("vincent").password("1234").roles("USER").and()
				.passwordEncoder(passwordEncoder());
		*/
		// auth.jdbcAuthentication().
		// authentification via un service custom, ici le notre requetant la base via repository
		// spring data
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/home/admin").hasRole("ADMIN")
								.antMatchers("/home").hasAnyRole("ADMIN", "USER")
								.antMatchers("/infos/**").authenticated()
								.antMatchers("/").authenticated()
			.and().httpBasic();
	}

	
	
	
}
