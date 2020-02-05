package com.edugroupe.springswagger2form.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Zoo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;
															private String nom;
															private String ville;
	@OneToMany(mappedBy = "zoo") @JsonIgnore 				private Set<Animal> animaux;
}
