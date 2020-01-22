package com.edugroupe.mybooksform.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.edugroupe.mybooksform.metier.Lecteur;

public interface LecteurRepository extends PagingAndSortingRepository<Lecteur, Integer>
{
	
	public Optional<Lecteur> findByUsername(String username);
	
	@Query("select lect from Lecteur lect left join fetch lect.roles where lect.username=:username")
	public Optional<Lecteur> findWithRoleByUsername(@Param("username") String username);
	
}
