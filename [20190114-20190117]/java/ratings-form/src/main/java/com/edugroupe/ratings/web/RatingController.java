package com.edugroupe.ratings.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.ratings.metier.Rating;
import com.edugroupe.ratings.repositories.RatingRepository;

@RestController
@RequestMapping("/ratings")
@CrossOrigin
public class RatingController {

	@Autowired private RatingRepository ratingRepository;
	
	@GetMapping("/{userid:[a-zA-Z0-9]+}")
	public List<Rating> findByuserId(@PathVariable("userid") String userId) {
		return ratingRepository.findByUserId(userId);
	}
	
}
