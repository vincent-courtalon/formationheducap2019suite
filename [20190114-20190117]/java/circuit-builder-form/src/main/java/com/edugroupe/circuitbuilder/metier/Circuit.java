package com.edugroupe.circuitbuilder.metier;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Circuit {
	private List<Hotel> hotels;
	private List<Trajet> trajets;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private double prix;

}
