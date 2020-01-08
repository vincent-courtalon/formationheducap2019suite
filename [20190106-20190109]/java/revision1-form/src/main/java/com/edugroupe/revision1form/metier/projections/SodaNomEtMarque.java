package com.edugroupe.revision1form.metier.projections;

import org.springframework.data.rest.core.config.Projection;

import com.edugroupe.revision1form.metier.Soda;

@Projection(name = "SodaNomEtMarque", types = Soda.class)
public interface SodaNomEtMarque {
	String getNom();
	String getMarque();

}
