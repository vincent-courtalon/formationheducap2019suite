package com.edugroupe.nanomaniaform.metier.projection;

import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.edugroupe.nanomaniaform.metier.Genre;
import com.edugroupe.nanomaniaform.metier.JeuxVideo;
import com.edugroupe.nanomaniaform.metier.PlateForme;

@Projection(name = "JeuxInfosComplet", types = JeuxVideo.class)
public interface JeuxInfosComplet extends JeuxAvecEditeur {
	Set<Genre> getGenres();
	Set<PlateForme> getPlateFormes();
}
