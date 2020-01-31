package com.edugroupe.springplanification.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springplanification.metier.Intervention;

public interface InterventionRepository extends PagingAndSortingRepository<Intervention, Integer> {

	Page<Intervention> findByIntervenantId(int intervenantId, Pageable page);
	List<Intervention> findByIntervenantIdAndDateIntervention(
											int intervenantId,
											LocalDate dateIntervention);
	
}
