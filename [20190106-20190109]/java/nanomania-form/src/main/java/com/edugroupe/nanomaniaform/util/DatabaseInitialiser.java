package com.edugroupe.nanomaniaform.util;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.edugroupe.nanomaniaform.metier.Editeur;
import com.edugroupe.nanomaniaform.metier.Genre;
import com.edugroupe.nanomaniaform.metier.JeuxVideo;
import com.edugroupe.nanomaniaform.metier.PlateForme;
import com.edugroupe.nanomaniaform.repositories.EditeurRepository;
import com.edugroupe.nanomaniaform.repositories.GenreRepository;
import com.edugroupe.nanomaniaform.repositories.JeuxVideoRepository;
import com.edugroupe.nanomaniaform.repositories.PlateFormeRepository;

@Service
public class DatabaseInitialiser implements ApplicationListener<ContextRefreshedEvent>  {

	@Autowired private JeuxVideoRepository jeuxVideoRepository;
	@Autowired private GenreRepository genreRepository;
	@Autowired private EditeurRepository editeurRepository;
	@Autowired private PlateFormeRepository plateFormeRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("verification initialisation base");
		if (jeuxVideoRepository.count() == 0 
			&& genreRepository.count() == 0
			&& editeurRepository.count() == 0
			&& plateFormeRepository.count() == 0) {
			System.out.println("base vide, initialiser la base");
			// pas de données dans la base, on initialise!
			Genre g1 = genreRepository.save(new Genre(0, "aventure", null));
			Genre g2 = genreRepository.save(new Genre(0, "rpg", null));
			Genre g3 = genreRepository.save(new Genre(0, "sport", null));
			
			Editeur e1 = editeurRepository.save(new Editeur(0, "ubisoft", "ubi@soft.com",null));
			Editeur e2 = editeurRepository.save(new Editeur(0, "rockstar", "rockstar@rockstar.com",null));
			
			PlateForme p1 = plateFormeRepository.save(new PlateForme(0, "xbox one", "microsoft", null));
			PlateForme p2 = plateFormeRepository.save(new PlateForme(0, "ps4", "sony", null));
			PlateForme p3 = plateFormeRepository.save(new PlateForme(0, "switch", "nintendo", null));
			
			JeuxVideo jv1 =	new JeuxVideo(0, "gta4", LocalDate.of(2015, 10, 12),
					new HashSet<>(), new HashSet<>(), e2); 
			jv1.getGenres().add(g1);
			jv1.getGenres().add(g2);
			jv1.getPlateFormes().add(p1);
			jv1.getPlateFormes().add(p2);
			jeuxVideoRepository.save(jv1);
			
			JeuxVideo jv2 =	new JeuxVideo(0, "monster hunter world", LocalDate.of(2016, 10, 12),
					new HashSet<>(), new HashSet<>(), e1); 
			jv2.getGenres().add(g1);
			jv2.getGenres().add(g2);
			jv2.getPlateFormes().add(p2);
			jv2.getPlateFormes().add(p3);
			jeuxVideoRepository.save(jv2);
			
			
			
		}
		
	}
	

}
