package com.edugroupe.mybooksform.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edugroupe.mybooksform.metier.Lecteur;

public class CustomUserDetails implements UserDetails {

	private final Lecteur lecteur;
	public CustomUserDetails(Lecteur lecteur) {this.lecteur = lecteur;}

	@Override
	public String getPassword() {return lecteur.getPassword();}
	@Override
	public String getUsername() {return lecteur.getUsername();}
	@Override
	public boolean isEnabled() {return lecteur.isEnabled();}
	@Override
	public boolean isAccountNonExpired() {return true;}
	@Override
	public boolean isAccountNonLocked() {return true;}
	@Override
	public boolean isCredentialsNonExpired() {return true;}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.lecteur.getRoles()
						   .stream()
						   .map(r -> new SimpleGrantedAuthority(r.getRolename()))
						   .collect(Collectors.toList());
					
	}



	

}
