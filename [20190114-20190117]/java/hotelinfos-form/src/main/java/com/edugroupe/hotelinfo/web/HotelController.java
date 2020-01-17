package com.edugroupe.hotelinfo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.hotelinfo.metier.Hotel;
import com.edugroupe.hotelinfo.repository.HotelRepository;

@RestController
@RequestMapping("/hotels")
@CrossOrigin
public class HotelController {

	@Autowired private HotelRepository hotelRepository;
	
	@GetMapping("/{ville}")
	public ResponseEntity<Hotel> hotelDeVille(@PathVariable("ville") String ville) {
		return hotelRepository.findByVille(ville)
							  .map(v -> new ResponseEntity<>(v, HttpStatus.OK))
							  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
}
