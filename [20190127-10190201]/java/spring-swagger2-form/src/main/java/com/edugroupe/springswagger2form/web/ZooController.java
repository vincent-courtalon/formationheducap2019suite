package com.edugroupe.springswagger2form.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.springswagger2form.metier.Zoo;
import com.edugroupe.springswagger2form.repositories.ZooRepository;

@RestController
@RequestMapping("/zoos")
public class ZooController {

	@Autowired private ZooRepository zooRepository;
	
	
	@GetMapping
	public Page<Zoo> findAll(@PageableDefault(page = 0, size = 10) Pageable page) {
		return zooRepository.findAll(page);
	}
	
	@GetMapping("/{id:[0-9]+}")
	public ResponseEntity<Zoo> findById(@PathVariable("id") int id) {
		return zooRepository.findById(id)
							.map(z -> new ResponseEntity<Zoo>(z, HttpStatus.OK))
							.orElse(new ResponseEntity<Zoo>(HttpStatus.NOT_FOUND));
	}
	
	
	
	
}
