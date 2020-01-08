package com.edugroupe.revision1form.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.revision1form.metier.Soda;
import com.edugroupe.revision1form.metier.projections.SodaDescription;
import com.edugroupe.revision1form.repositories.SodaRepository;

@RestController
@RequestMapping(value = "/sodaweb")
public class SodaController {
	
	
	private final ProjectionFactory projectionFactory;
	
	@Autowired
	public SodaController(ProjectionFactory projectionFactory) {
		this.projectionFactory = projectionFactory;
	}

	@Autowired
	private SodaRepository sodaRepository;
	
	@GetMapping
	public Iterable<Soda> findAllSoda() {
		return sodaRepository.findAll();
	}
	
	@GetMapping(value = "/description")
	public Page<SodaDescription> findAllSodaDescription(@PageableDefault Pageable page) {
		return sodaRepository
				.findAll(page)
				.map(soda -> projectionFactory.createProjection(SodaDescription.class, soda));
	}
	
	
	
}
