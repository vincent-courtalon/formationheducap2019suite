package com.edugroupe.revision2form.metier.projection;

import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.edugroupe.revision2form.metier.Caracteristique;
import com.edugroupe.revision2form.metier.Terroir;
import com.edugroupe.revision2form.metier.Vin;

@Projection(name="VinAvecCaracteristiques", types = Vin.class)
public interface VinAvecCaracteristiques {
	int getId();
	String getNom();
	int getAnnee();
	Terroir getTerroir();
	Set<Caracteristique> getCaracteristiques();
}
