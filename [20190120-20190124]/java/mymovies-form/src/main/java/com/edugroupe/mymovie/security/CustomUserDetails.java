package com.edugroupe.mymovie.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edugroupe.mymovie.metier.Utilisateur;


public class CustomUserDetails implements UserDetails {

	private final Utilisateur utilisateur;
	
	public CustomUserDetails(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String getUsername() {return utilisateur.getLogin();}
	@Override
	public String getPassword() {return utilisateur.getPassword();}
	@Override
	public boolean isAccountNonExpired() { return true;}
	@Override
	public boolean isAccountNonLocked() { return true;}
	@Override
	public boolean isCredentialsNonExpired() {return true;}
	@Override
	public boolean isEnabled() { return utilisateur.isEnabled();}
	
	// renvoie les roles, ou authorisations de notre utilisateur
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.utilisateur.getRoles().stream()
					.map(r -> r.getRoleName())
					.map(rolename -> new SimpleGrantedAuthority(rolename))
					.collect(Collectors.toList());
		
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	

}
