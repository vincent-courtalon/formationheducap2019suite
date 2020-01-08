package com.edugroupe.revision2form.metier.projection;

import org.springframework.data.rest.core.config.Projection;

import com.edugroupe.revision2form.metier.Terroir;
import com.edugroupe.revision2form.metier.Vin;

@Projection(name = "VinAvecTerroir", types = Vin.class)
public interface VinAvecTerroir {
	String getNom();
	int getAnnee();
	Terroir getTerroir();

}
