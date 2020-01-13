package com.edugroupe.nanomaniaform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.edugroupe.nanomaniaform.metier.PlateForme;

@RepositoryRestResource
public interface PlateFormeRepository extends PagingAndSortingRepository<PlateForme, Integer> {

}
