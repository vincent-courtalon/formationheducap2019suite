package com.edugroupe.revision2form.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.revision2form.metier.Terroir;
import com.edugroupe.revision2form.metier.Vin;
import com.edugroupe.revision2form.metier.projection.VinAvecCaracteristiques;
import com.edugroupe.revision2form.repositories.TerroirRepository;
import com.edugroupe.revision2form.repositories.VinRepository;

@CrossOrigin
@RestController
@RequestMapping("/vins")
public class VinController {
	
	private final ProjectionFactory projectionFactory;

	@Autowired
	public VinController(ProjectionFactory projectionFactory) {
		this.projectionFactory = projectionFactory;
	}

	@Autowired private VinRepository vinRepository;
	@Autowired private TerroirRepository terroirRepository;
	
	@GetMapping
	public Page<VinAvecCaracteristiques> findAll(@PageableDefault Pageable page,
												 @RequestParam("terroirId") Optional<Integer> terroirId) {
		if (terroirId.isPresent()) {
			return vinRepository.findByTerroirId(terroirId.get(), page)
					.map(vin -> 
					projectionFactory.createProjection(VinAvecCaracteristiques.class, vin));			
		}
		else
			return vinRepository.findAll(page)
								.map(vin -> 
								projectionFactory.createProjection(VinAvecCaracteristiques.class, vin));
	}
	
	@GetMapping(value = "/{id:[0-9]+}")
	public ResponseEntity<VinAvecCaracteristiques> findById(@PathVariable("id") int id) {
		return this.vinRepository.findById(id)
					.map(
				v -> new ResponseEntity<>(
						projectionFactory.createProjection(VinAvecCaracteristiques.class, v),
						HttpStatus.OK))
					.orElse(
					new ResponseEntity<>(HttpStatus.NOT_FOUND));			
	}
	
	@GetMapping(value="/terroirs")
	public Iterable<Terroir> listeTerroirs() {
		return this.terroirRepository.findAll();
	}
/*	
	@PutMapping(value="/{id:[0-9]+}")
	public ResponseEntity<Vin> updateVin(@PathVariable("id") int id,
										 @RequestParam("nom") String nom,
										 @RequestParam("annee") int annee,
										 @RequestParam("idTerroir") int idTerroir) {
		Optional<Vin> vin = this.vinRepository.findById(id);
		if (!vin.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Vin editedVin = vin.get();
		editedVin.setAnnee(annee);
		editedVin.setNom(nom);
		editedVin.setTerroir(terroirRepository.findById(idTerroir).orElse(null));
		editedVin = vinRepository.save(editedVin);
		return new ResponseEntity<Vin>(editedVin, HttpStatus.ACCEPTED);
	}
	*/
	
	@PutMapping(value="/{id:[0-9]+}")
	public ResponseEntity<Vin> updateVin(@PathVariable("id") int id,
										 @RequestBody Vin vin,
										 @RequestParam("idTerroir") int idTerroir) {
		Optional<Vin> originalvin = this.vinRepository.findById(id);
		if (!originalvin.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Vin editedVin = originalvin.get();
		editedVin.setAnnee(vin.getAnnee());
		editedVin.setNom(vin.getNom());
		editedVin.setTerroir(terroirRepository.findById(idTerroir).orElse(null));
		editedVin = vinRepository.save(editedVin);
		return new ResponseEntity<Vin>(editedVin, HttpStatus.ACCEPTED);
	}

}
