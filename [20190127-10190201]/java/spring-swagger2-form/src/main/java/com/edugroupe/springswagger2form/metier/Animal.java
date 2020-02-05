package com.edugroupe.springswagger2form.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Animal {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;
															private String nom;
															private String espece;
	@ManyToOne 												private Zoo zoo;
	
	

}
