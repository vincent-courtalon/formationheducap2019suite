package com.edugroupe.nanomaniaform.metier.projection;

import java.time.LocalDate;

import org.springframework.data.rest.core.config.Projection;

import com.edugroupe.nanomaniaform.metier.Editeur;
import com.edugroupe.nanomaniaform.metier.JeuxVideo;

@Projection(name = "JeuxAvecEditeur",types = JeuxVideo.class)
public interface JeuxAvecEditeur {
	int getId();
	String getNom();
	LocalDate getDateSortie();
	Editeur getEditeur();

}
