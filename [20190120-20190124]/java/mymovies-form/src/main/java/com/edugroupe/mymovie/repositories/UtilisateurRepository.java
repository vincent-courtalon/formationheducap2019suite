package com.edugroupe.mymovie.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.edugroupe.mymovie.metier.Utilisateur;


public interface UtilisateurRepository extends PagingAndSortingRepository<Utilisateur, Integer> {

	Optional<Utilisateur> findByLogin(String login);
	
	@Query("select u from Utilisateur u left join fetch u.roles where u.login=:login")
	Optional<Utilisateur> findWithRoleByLogin(@Param("login") String login);
	
}
