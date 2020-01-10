package com.edugroupe.revision2form.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.revision2form.metier.Caracteristique;
import com.edugroupe.revision2form.repositories.CaracteristiqueRespository;

@RestController
@RequestMapping("/caracteristiques")
@CrossOrigin
public class CaracteristiqueController {

	@Autowired
	private CaracteristiqueRespository caracteristiqueRespository;
	
	@GetMapping
	public Iterable<Caracteristique> findAll() {
		return this.caracteristiqueRespository.findAll();
	}
	
	
	
	
}
