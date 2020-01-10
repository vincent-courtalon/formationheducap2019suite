package com.edugroupe.revision2form.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.edugroupe.revision2form.metier.Vin;

@RepositoryRestResource
public interface VinRepository extends PagingAndSortingRepository<Vin, Integer> {

	@RestResource(path="byTerroir", rel="customFindMethod")
	Page<Vin> findByTerroirId(@Param("terroirId") int terroirId, Pageable page);
	
	
	@RestResource(path="byCaracteristiques", rel="customFindMethod")
	Page<Vin> findDistinctByCaracteristiquesIdIn(
			@Param("caracteristiquesId")List<Integer> caracteristiquesId,
			Pageable page);
	
	@RestResource(path="byCaracteristiquesAndTerroir", rel="customFindMethod")
	Page<Vin> findDistinctByCaracteristiquesIdInAndTerroirId(
			@Param("caracteristiquesId")List<Integer> caracteristiquesId,
			@Param("terroirId") int terroirId,
			Pageable page);
	
}
