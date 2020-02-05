package com.edugroupe.springswagger2form.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.springswagger2form.metier.Zoo;
import com.edugroupe.springswagger2form.repositories.ZooRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/zoos")
@Api(value = "Api des zoos",
	description = "operations de listage et de manipulation des zoos")
public class ZooController {

	@Autowired private ZooRepository zooRepository;
	
	
	@GetMapping
	@ApiOperation(value = "voir la liste des zoos paginées",
				produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code=200, message = "Page retournée avec succès")
	})
	public Page<Zoo> findAll(@PageableDefault(page = 0, size = 10) Pageable page) {
		return zooRepository.findAll(page);
	}
	
	@GetMapping("/{id:[0-9]+}")
	@ApiOperation(value="consulter un zoo via son identifiant")
	@ApiResponses(value = {
			@ApiResponse(code=200, message = "zoo retourné avec success"),
			@ApiResponse(code=404, message = "ce zoo n'existe pas")
	})
	public ResponseEntity<Zoo> findById(
			@ApiParam(value = "identifiant du zoo, entier positif", example = "3") @PathVariable("id") int id) {
		return zooRepository.findById(id)
							.map(z -> new ResponseEntity<Zoo>(z, HttpStatus.OK))
							.orElse(new ResponseEntity<Zoo>(HttpStatus.NOT_FOUND));
	}
	
	
	@PostMapping
	@ApiOperation(value="creer un nouveau zoo")
	@ApiResponses(value = {
			@ApiResponse(code=201, message = "zoo crée avec success"),
			@ApiResponse(code=400, message = "impossible de creer ce zoo, id invalide")
	})
	public ResponseEntity<Zoo> createZoo(@RequestBody Zoo zoo) {
		if (zoo.getId() != 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Zoo>(zooRepository.save(zoo), HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiOperation(value="mettre à jour un zoo")
	@ApiResponses(value = {
			@ApiResponse(code=202, message = "zoo mis à jour avec success"),
			@ApiResponse(code=400, message = "impossible de mettre à jour ce zoo, id invalide"),
			@ApiResponse(code=404, message = "impossible de mettre à jour ce zoo, zoo inconnu")
	})	
	public ResponseEntity<Zoo> updateZoo(@RequestBody Zoo zoo) {
		if (zoo.getId() == 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Optional<Zoo> oldZoo = zooRepository.findById(zoo.getId());
		if (!oldZoo.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Zoo newZoo = oldZoo.get();
		newZoo.setNom(zoo.getNom());
		newZoo.setVille(zoo.getVille());
		return new ResponseEntity<Zoo>(zooRepository.save(newZoo), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id:[0-9]+}")
	@ApiOperation(value="effacer un zoo")
	@ApiResponses(value = {
			@ApiResponse(code=202, message = "zoo supprimé avec success"),
			@ApiResponse(code=400, message = "impossible de supprimer ce zoo, id invalide"),
			@ApiResponse(code=404, message = "impossible de supprimer ce zoo, zoo inconnu")
	})	
	public ResponseEntity<String> deleteZoo(@ApiParam(value = "identifiant du zoo, entier positif", example = "3") 
											@PathVariable("id") int id) {
		if (id == 0)
			return new ResponseEntity<String>("id invalide", HttpStatus.BAD_REQUEST);
		if (!zooRepository.existsById(id))
			return new ResponseEntity<String>("zoo inconnu", HttpStatus.NOT_FOUND);
		zooRepository.deleteById(id);
		return new ResponseEntity<String>("zoo d'id " + id + " supprimé", HttpStatus.ACCEPTED);
	}
	
	
	
}
