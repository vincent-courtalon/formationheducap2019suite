package com.edugroupe.springplanification.metier;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Intervention {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 	private int id;
																private String description;
																private LocalDate dateIntervention;
																private int heureDebut;
																private int heureFin;
																private String lieu;
	@ManyToOne													private Intervenant intervenant;

}
