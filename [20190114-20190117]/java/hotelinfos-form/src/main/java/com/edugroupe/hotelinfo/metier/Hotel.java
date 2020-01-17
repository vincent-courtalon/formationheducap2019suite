package com.edugroupe.hotelinfo.metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Hotel {
	@JsonIgnore
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;
	@Column(length=100,nullable = false, unique = true) 	private String ville;
															private String nom;
															private double tarifJour;
	
}
