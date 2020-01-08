package com.edugroupe.revision2form.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Caracteristique {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
																private String libelle;
	@ManyToMany(mappedBy = "caracteristiques") 	@JsonIgnore		private Set<Vin> vins;
	
	@PreRemove
	public void avantEffacement() {
		if (this.getVins() != null) {
			for (Vin v : this.getVins()) {
				// je retire ma caracteristique du vin associé
				v.getCaracteristiques().remove(this);
			}
			this.getVins().clear();
		}
	}
	
}
