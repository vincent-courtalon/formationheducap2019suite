package com.edugroupe.firstsecurity.util;

import java.util.Collections;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edugroupe.firstsecurity.metier.Role;
import com.edugroupe.firstsecurity.metier.Utilisateur;
import com.edugroupe.firstsecurity.repositories.RoleRepository;
import com.edugroupe.firstsecurity.repositories.UtilisateurRepository;

@Service
public class DatabaseInitialiser implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired private UtilisateurRepository utilisateurRepository;
	@Autowired private RoleRepository roleRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (utilisateurRepository.count() == 0 && roleRepository.count() == 0) {
			Role r1 = new Role(0, "ROLE_ADMIN", null);
			Role r2 = new Role(0, "ROLE_USER", null);
			Role r3 = new Role(0, "ROLE_VISITEUR", null);
			r1 = roleRepository.save(r1);
			r2 = roleRepository.save(r2);
			r3 = roleRepository.save(r3);
			// creation utilisateur admin avec roles admin et user
			Utilisateur u1 = new Utilisateur(0,
											"admin",
											passwordEncoder.encode("admin"),
											true,
											new HashSet<>());
			u1.getRoles().add(r1);
			u1.getRoles().add(r2);
			utilisateurRepository.save(u1);
			
			// creation utilisateur vincent avec role user
			Utilisateur u2 = new Utilisateur(0,
											"vincent",
											passwordEncoder.encode("1234"),
											true,
											new HashSet<>());
			u2.getRoles().add(r2);
			u2.getRoles().add(r3);
			utilisateurRepository.save(u2);
			
		}
		
	}

}
