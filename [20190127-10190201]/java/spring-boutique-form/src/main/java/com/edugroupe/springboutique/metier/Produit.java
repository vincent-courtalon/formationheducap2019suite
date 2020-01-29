package com.edugroupe.springboutique.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Produit {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
																private String nom;
																private double prix;
																private double poids;
}
