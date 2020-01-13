package com.edugroupe.nanomaniaform.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.edugroupe.nanomaniaform.metier.JeuxVideo;

@RepositoryRestResource
public interface JeuxVideoRepository extends PagingAndSortingRepository<JeuxVideo, Integer> {

	@RestResource(path = "byEditeur", rel = "customFindMethod")
	Page<JeuxVideo> findByEditeurId(@Param("editeurId") int editeurId, Pageable page); 
}
