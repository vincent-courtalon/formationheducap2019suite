package com.edugroupe.trajetsinfo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.trajetsinfo.dao.TrajetDAO;
import com.edugroupe.trajetsinfo.metier.Trajet;

@RestController
@RequestMapping("/trajets")
@CrossOrigin
public class trajetController {

	@Autowired	private TrajetDAO trajetDAO;
	
	
	@GetMapping()
	public ResponseEntity<Trajet> hotelDeVille(
			@RequestParam("depart") String depart,
			@RequestParam("arrivee") String arrivee) {
		return trajetDAO.trouverParDepartArrivee(depart, arrivee)
							  .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
							  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
