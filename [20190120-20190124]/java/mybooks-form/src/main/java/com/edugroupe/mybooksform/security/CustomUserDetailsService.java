package com.edugroupe.mybooksform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edugroupe.mybooksform.repositories.LecteurRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired private LecteurRepository lecteurRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return lecteurRepository
					.findWithRoleByUsername(username)
					.map(lect -> new CustomUserDetails(lect))
					.orElseThrow(() -> new UsernameNotFoundException("login/password invalide"));
	}

}
