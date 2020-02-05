package com.edugroupe.springswagger2form.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ApiModel(description = " details concernant un animal")
public class Animal {
	@ApiModelProperty(notes = "l'id généré par la base de donnée")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;
															private String nom;
															private String espece;															
	@ManyToOne 												private Zoo zoo;
	

}
