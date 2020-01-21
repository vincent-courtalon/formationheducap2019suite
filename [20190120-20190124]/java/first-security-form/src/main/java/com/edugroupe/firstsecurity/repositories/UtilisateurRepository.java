package com.edugroupe.firstsecurity.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.firstsecurity.metier.Utilisateur;

public interface UtilisateurRepository extends PagingAndSortingRepository<Utilisateur, Integer> {

	Optional<Utilisateur> findByLogin(String login);
	
}
