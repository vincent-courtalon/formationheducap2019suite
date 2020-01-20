package com.edugroupe.circuitbuilder.web;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.edugroupe.circuitbuilder.metier.Circuit;
import com.edugroupe.circuitbuilder.metier.Hotel;
import com.edugroupe.circuitbuilder.metier.Trajet;

@RestController
@RequestMapping("/circuits")
@CrossOrigin
public class CircuitController {

	final private RestTemplate restTemplate;
	
	@Autowired
	public CircuitController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	@GetMapping("/build")
	public ResponseEntity<Object> buildCircuit(
			@RequestParam("dateDebut") String dateDebut,
			@RequestParam("villes") List<String> villes) {
		if (villes.size() < 2) {
			return new ResponseEntity<>(
					Collections.singletonMap("error", "fournir au moins deux villes"),
											HttpStatus.BAD_REQUEST);
		}
		Circuit c = new Circuit();
		c.setDateDebut(LocalDate.parse(dateDebut, DateTimeFormatter.ISO_LOCAL_DATE));
		c.setHotels(villes.stream().map(v -> {
			return restTemplate.getForObject("http://hotelsinfos/hotels/" + v, Hotel.class);
		}).collect(Collectors.toList()));
		
		List<Trajet> trajets = new ArrayList<>();
		for (int pos = 0; pos < villes.size() - 1; pos++) {
			String depart = villes.get(pos);
			String arrivee = villes.get(pos + 1);
			trajets.add(restTemplate.getForObject(
					"http://trajetsinfos/trajets?depart="
					+ depart + "&arrivee=" + arrivee, Trajet.class));
		}
		// j'ai récupéré toutes mes données
		c.setTrajets(trajets);
		LocalDate currentDate = c.getDateDebut();
		double total = 0.0;
		for (int pos = 0; pos < c.getHotels().size() - 1; pos++) {
			if (	currentDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) 
				||	currentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
				total += c.getHotels().get(pos).getTarifJour() * 1.2;// +20% le week-end
			else
				total += c.getHotels().get(pos).getTarifJour();
			currentDate = currentDate.plusDays(1);
			double dist = c.getTrajets().get(pos).getDist_km();
			total += dist * 0.1;
			currentDate = currentDate.plusDays((long)(Math.floor(dist / 500) + 1));
		}
		c.setDateFin(currentDate);
		c.setPrix(total);
		return new ResponseEntity<Object>(c, HttpStatus.OK);
	}
	
	
}
