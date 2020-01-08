package com.edugroupe.revision1form.metier;

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
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class Soda {
	// mysql -> GenerationType.IDENTITY = colonne autoincrement
	//postgresql -> GenerationType.SEQUENCE
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "denomination", length = 100, nullable = false)
	private String nom;
	private String marque;
	@JsonIgnore
	private double prix;

}
