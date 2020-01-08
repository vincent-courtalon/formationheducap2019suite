package com.edugroupe.revision2form.repositories;

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
	
}
