package com.edugroupe.springswagger2form.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.springswagger2form.metier.Animal;
import com.edugroupe.springswagger2form.metier.Zoo;
import com.edugroupe.springswagger2form.repositories.AnimalRepository;
import com.edugroupe.springswagger2form.repositories.ZooRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/animaux")
@Api(value = "Api des animaux",
description = "operations de listage et de manipulation des animaux")
@CrossOrigin
public class AnimalController {
	
	@Autowired private AnimalRepository animalRepository;
	@Autowired private ZooRepository zooRepository;
	
	@GetMapping
	@ApiOperation(value = "voir la liste des animaux paginées",
				produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code=200, message = "Page retournée avec succès")
	})
	public Page<Animal> findAll(@PageableDefault(page = 0, size = 10) Pageable page) {
		return animalRepository.findAll(page);
	}

	@GetMapping("/{id:[0-9]+}")
	@ApiOperation(value="consulter un animal via son identifiant")
	@ApiResponses(value = {
			@ApiResponse(code=200, message = "animal retourné avec success"),
			@ApiResponse(code=404, message = "cet animal n'existe pas")
	})
	public ResponseEntity<Animal> findById(@ApiParam(value = "identifiant du zoo, entier positif", example = "3")
											@PathVariable("id") int id) {
		return animalRepository.findById(id)
								.map(a -> new ResponseEntity<>(a, HttpStatus.OK))
								.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	@ApiOperation(value="creer un nouvel animal")
	@ApiResponses(value = {
			@ApiResponse(code=201, message = "animal crée avec success"),
			@ApiResponse(code=400, message = "impossible de creer cet animal, id invalide ou zoo inconnu")
	})
	public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal,
												@RequestParam("zooId") Optional<Integer> zooId) {
		if (animal.getId() != 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (zooId.isPresent()) {
			Optional<Zoo> zoo = zooRepository.findById(zooId.get());
			if (!zoo.isPresent())
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			animal.setZoo(zoo.get());
		}
		return new ResponseEntity<Animal>(animalRepository.save(animal), HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiOperation(value="mettre à jour un animal")
	@ApiResponses(value = {
			@ApiResponse(code=202, message = "animal mis à jour avec success"),
			@ApiResponse(code=400, message = "impossible de mettre à jour cet animal, id invalide ou zoo inconnu"),
			@ApiResponse(code=404, message = "impossible de mettre à jour cet animal, animal inconnu")
	})	
	public ResponseEntity<Animal> updateAnimal(@RequestBody Animal animal,
											   @RequestParam("zooId") Optional<Integer> zooId) {
		if (animal.getId() == 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Optional<Animal> oldAnimal = animalRepository.findById(animal.getId());
		if (!oldAnimal.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Animal newAnimal = oldAnimal.get();
		if (zooId.isPresent()) {
			Optional<Zoo> zoo = zooRepository.findById(zooId.get());
			if (zoo.isPresent())
				newAnimal.setZoo(zoo.get());
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else
			newAnimal.setZoo(null);
		
		newAnimal.setEspece(animal.getEspece());
		newAnimal.setNom(animal.getNom());
		return new ResponseEntity<Animal>(animalRepository.save(newAnimal), HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/{id:[0-9]+}")
	@ApiOperation(value="effacer un animal")
	@ApiResponses(value = {
			@ApiResponse(code=202, message = "animal supprimé avec success"),
			@ApiResponse(code=400, message = "impossible de supprimer cet animal, id invalide"),
			@ApiResponse(code=404, message = "impossible de supprimer cet animal, animal inconnu")
	})	
	public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
		if (id == 0)
			return new ResponseEntity<>("id invalide", HttpStatus.BAD_REQUEST);
		if (!animalRepository.existsById(id))
			return new ResponseEntity<>("animal inconnu", HttpStatus.NOT_FOUND);
		animalRepository.deleteById(id);
		return new ResponseEntity<String>("animal d'id " + id + " supprimé", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/zoo/{zooId:[0-9]+}")
	@ApiOperation(value = "liste les animaux d'un zoo")
	@ApiResponses(value = {
			@ApiResponse(code=200, message = "liste des animaux du zoo retournée"),
			@ApiResponse(code=404, message = "zoo inconnu")
	})		
	public ResponseEntity<List<Animal>> findbyZoo(@PathVariable("zooId") int zooId) {
		if (!zooRepository.existsById(zooId))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Animal>>(animalRepository.findByZooId(zooId), HttpStatus.OK);
	}
	
	
}
