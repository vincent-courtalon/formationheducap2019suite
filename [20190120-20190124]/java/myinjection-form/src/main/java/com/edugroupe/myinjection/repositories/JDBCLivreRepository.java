package com.edugroupe.myinjection.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.edugroupe.myinjection.metier.Lecture;

@Service
public class JDBCLivreRepository {

	@Autowired private JdbcTemplate jdbcTemplate;

	/*
	 * une méthode qui me renvoie la liste de toutes lectures (titre + utilisateur)
	 * correspondant a une recherche, mais en excluant admin des resultats
	 * 
	 */
	public List<Lecture> findByTitre(String searchterm) {
		 
		String sql = "SELECT Livre.titre, lecteur.username FROM `Livre` \n" + 
				"		JOIN lecteur_livres \n" + 
				"		ON livre.id = lecteur_livres.livres_id \n" + 
				"        JOIN lecteur ON lecteur.id = lecteur_livres.lecteurs_id \n" + 
				"        WHERE livre.titre LIKE '%" + searchterm + "%' AND lecteur.id != 1";
		
		return jdbcTemplate.query(
				sql,
				new Object[] {},
				(rs, rownum) -> new Lecture(rs.getString("titre"), rs.getString("username"))
				);
	}
	
	public int updateEmail(String email, int userId) {
		String sql = "UPDATE Lecteur set lecteur.email='" + email 
						+ "' WHERE lecteur.id=" + userId;
		return jdbcTemplate.update(sql);
		
	}
	
}
