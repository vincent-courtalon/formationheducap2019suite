package com.edugroupe.springplanification.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.springplanification.metier.Intervenant;
import com.edugroupe.springplanification.metier.Intervention;
import com.edugroupe.springplanification.repositories.IntervenantRepository;
import com.edugroupe.springplanification.repositories.InterventionRepository;

@RestController
@RequestMapping("/interventions")
public class InterventionController {
	
	
	@Autowired private InterventionRepository interventionRepository;
	@Autowired private IntervenantRepository intervenantRepository;


	@GetMapping
	public Page<Intervention> findAll(@PageableDefault(page= 0, size = 10) Pageable page) {
		return interventionRepository.findAll(page);
	}
	
	@GetMapping("/intervenant/{intervenantId:[0-9]+}")
	public ResponseEntity<Page<Intervention>> findByIntervenant(
									@PathVariable("intervenantId") int intervenantId,
									@PageableDefault(page= 0, size = 10) Pageable page	) {
		if (!intervenantRepository.existsById(intervenantId))
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Page<Intervention>>(
				interventionRepository.findByIntervenantId(intervenantId, page), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Intervention> createIntervention(
										@RequestBody Intervention intervention,
										@RequestParam("intervenantId") int intervenantId) {
		Optional<Intervenant> intervenant = intervenantRepository.findById(intervenantId);
		if (!intervenant.isPresent())
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		if (intervention.getId() > 0)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
		intervention.setIntervenant(intervenant.get());
		
		// controle sur les dates
		if (!checkIntervenantDisponibility(intervention, intervenant.get()))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		return new ResponseEntity<Intervention>(interventionRepository.save(intervention),
												HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Intervention> changeIntervention(
						@RequestParam("interventionId") int interventionId,
						@RequestParam("intervenantId") Optional<Integer> intervenantId,
						@RequestParam("dateDebut") Optional<String> dateDebut,
						@RequestParam("heureDebut") Optional<Integer> heureDebut,
						@RequestParam("heureFin") Optional<Integer> heureFin) {
		Optional<Intervention> inter = interventionRepository.findById(interventionId);
		if (!inter.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Intervention intervention = inter.get();
		// on a l'intervention
		if (intervenantId.isPresent()) {
			Optional<Intervenant> it = intervenantRepository.findById(intervenantId.get());
			if (it.isPresent())
				intervention.setIntervenant(it.get());
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// on a gerer l'intervenant
		
		if (dateDebut.isPresent()) intervention.setDateIntervention(LocalDate.parse(dateDebut.get()));
		if (heureDebut.isPresent()) intervention.setHeureDebut(heureDebut.get());
		if (heureFin.isPresent()) intervention.setHeureFin(heureFin.get());
		
		if (checkIntervenantDisponibility(intervention,intervention.getIntervenant()))
			return new ResponseEntity<Intervention>(interventionRepository.save(intervention), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	private boolean checkIntervenantDisponibility(final Intervention intervention,
												  final Intervenant intervenant) {
		
		if (intervention.getHeureDebut() < 9 || 
			intervention.getHeureFin() > 18 ||
			intervention.getHeureDebut() >= intervention.getHeureFin() ||
			intervention.getDateIntervention().minusDays(1).isBefore(LocalDate.now()))
			return false;
		List<Intervention> interventionsofDay = 
							interventionRepository.findByIntervenantIdAndDateIntervention(
															intervenant.getId(),
															intervention.getDateIntervention());
		// la liste des interventions déjà existante sur  la date demandée
		return interventionsofDay.stream()
						  		.filter(cur_inter -> cur_inter.getId() != intervention.getId()) // elimine ma propre intervention si elle est sur le même jour
						  		.allMatch(cur_inter -> 
						  			cur_inter.getHeureFin() <= intervention.getHeureDebut() ||
						  			intervention.getHeureFin() <= cur_inter.getHeureDebut());
	}
	
}
