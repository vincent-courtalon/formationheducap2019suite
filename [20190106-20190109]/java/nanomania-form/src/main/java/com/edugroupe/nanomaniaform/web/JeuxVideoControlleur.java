package com.edugroupe.nanomaniaform.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.nanomaniaform.metier.Editeur;
import com.edugroupe.nanomaniaform.metier.JeuxVideo;
import com.edugroupe.nanomaniaform.metier.projection.JeuxAvecEditeur;
import com.edugroupe.nanomaniaform.repositories.EditeurRepository;
import com.edugroupe.nanomaniaform.repositories.JeuxVideoRepository;

import lombok.Getter;

@RestController
@RequestMapping("/jeuxvideos")
@CrossOrigin
public class JeuxVideoControlleur {

	@Autowired	private JeuxVideoRepository jeuxVideoRepository;
	@Autowired	private EditeurRepository editeurRepository;
	
	private final ProjectionFactory projectionFactory;

	@Autowired
	public JeuxVideoControlleur(ProjectionFactory projectionFactory) {
		this.projectionFactory = projectionFactory;
	}
	

	@GetMapping()
	public Page<JeuxAvecEditeur> findAll(
			@PageableDefault(page = 0, size = 10) Pageable page,
			@RequestParam("editeurId") Optional<Integer> editeurId) {
		if (editeurId.isPresent()) {
			return jeuxVideoRepository.findByEditeurId(editeurId.get(), page).map(
					jv -> projectionFactory.createProjection(JeuxAvecEditeur.class, jv));
		}
		else {
			return jeuxVideoRepository.findAll(page).map(
					jv -> projectionFactory.createProjection(JeuxAvecEditeur.class, jv));			
		}
		
	}
	
	@GetMapping("/editeurs")
	public Iterable<Editeur> listeEditeur() {
		return this.editeurRepository.findAll();
	}
	
	
}
