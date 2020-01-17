package com.edugroupe.trajetsinfo.metier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Trajet {
	private String depart;
	private String arrivee;
	private double dist_km;

}
