package com.edugroupe.junitandspring.metier;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor  @ToString
public class Produit {
	private  double prixMaximum;
	
	private int id;
	private String nom;
	private double prix;
	private double poids;
	
	public Produit(int id, String nom, double prix, double poids) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.poids = poids;
	}

	
}
