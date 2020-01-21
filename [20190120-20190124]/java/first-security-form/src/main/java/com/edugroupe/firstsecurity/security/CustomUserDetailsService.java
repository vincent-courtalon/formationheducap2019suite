package com.edugroupe.firstsecurity.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edugroupe.firstsecurity.metier.Utilisateur;
import com.edugroupe.firstsecurity.repositories.UtilisateurRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

	@Autowired private UtilisateurRepository utilisateurRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utilisateur> u = utilisateurRepository.findWithRoleByLogin(username);
		if (!u.isPresent())
			throw new UsernameNotFoundException("login/password invalide");
		
		return new CustomUserDetails(u.get());
	}
	
	
	
}
