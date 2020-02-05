package com.edugroupe.springswagger2form.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ApiModel(description = " details concernant un zoo")
public class Zoo {
	@ApiModelProperty(notes = "l'id généré par la base de donnée")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)				private int id;
	@ApiModelProperty(notes = "le nom du zoo",allowEmptyValue = false)	private String nom;
																		private String ville;
	@OneToMany(mappedBy = "zoo") @JsonIgnore 							private Set<Animal> animaux;
	
	@PreRemove
	public void avantEffacement() {
		if (animaux != null) {
			for (Animal a : animaux)
				a.setZoo(null);
			animaux.clear();
		}
	}
}
