package com.edugroupe.mybooksform.util;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edugroupe.mybooksform.metier.Lecteur;
import com.edugroupe.mybooksform.metier.Livre;
import com.edugroupe.mybooksform.metier.Role;
import com.edugroupe.mybooksform.repositories.LecteurRepository;
import com.edugroupe.mybooksform.repositories.LivreRepository;
import com.edugroupe.mybooksform.repositories.RoleRepository;

@Service
public class DatabaseInitialiser implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired private LecteurRepository lecteurRepository;
	@Autowired private RoleRepository roleRepository;
	@Autowired private LivreRepository livreRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (lecteurRepository.count() == 0 
			&& roleRepository.count() == 0
			&& livreRepository.count() == 0) {
			System.out.println("initialisation de la base de donnée vide");
			
			Role r1 = roleRepository.save(new Role(0, "ROLE_ADMIN", null));
			Role r2 = roleRepository.save(new Role(0, "ROLE_USER", null));
			
			Livre l1 = livreRepository.save(new Livre(0, "20000 lieux sous les mers", "35431324354", null));
			Livre l2 = livreRepository.save(new Livre(0, "java acrobatique", "876213431", null));
			Livre l3 = livreRepository.save(new Livre(0, "angular de combat", "64312131321", null));
			Livre l4 = livreRepository.save(new Livre(0, "l'ile flottante au trésors", "399432187", null));
			
			Lecteur lct1 = new Lecteur(	0, 
										"admin",
										passwordEncoder.encode("admin"),
										"admin@mybooks.com",
										true,
										new HashSet<>(),
										new HashSet<>());
			lct1.getRoles().add(r1);
			lct1.getRoles().add(r2);
			lct1.getLivres().add(l1);
			lct1.getLivres().add(l2);
			lecteurRepository.save(lct1);
			
			Lecteur lct2 = new Lecteur(	0, 
					"vincent",
					passwordEncoder.encode("1234"),
					"vincent@mybooks.com",
					true,
					new HashSet<>(),
					new HashSet<>());
			lct2.getRoles().add(r2);
			lct2.getLivres().add(l2);
			lct2.getLivres().add(l3);
			lecteurRepository.save(lct2);
		}
	}

	
}
