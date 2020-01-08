package com.edugroupe.revision1form.metier.projections;

import org.springframework.data.rest.core.config.Projection;

import com.edugroupe.revision1form.metier.Soda;

@Projection(name = "SodaAvecPrix", types = Soda.class)
public interface SodaAvecPrix {
	int getId();
	String getNom();
	String getMarque();
	double getPrix();
}
