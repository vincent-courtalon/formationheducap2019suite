package com.edugroupe.revision1form.metier.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.edugroupe.revision1form.metier.Soda;

@Projection(name = "SodaDescription", types = Soda.class)
public interface SodaDescription {
	
	@Value("#{target.nom + ' ' + target.marque}")
	String getDescription();
	
	double getPrix();

}
