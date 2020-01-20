package com.edugroupe.trajetsinfo.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edugroupe.trajetsinfo.metier.Trajet;

@Service
public class TrajetDAO {
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	public Optional<Trajet> trouverParDepartArrivee(String depart, String arrivee) {
		
		Query q = em.createNativeQuery(
				"SELECT v1.nom as depart,\n" + 
				"        v2.nom as arrivee,\n" + 
				"    	ST_Distance_Sphere(v1.coordonnee, v2.coordonnee) / 1000.0 as dist_km\n" + 
				"    FROM `ville` as v1\n" + 
				"	CROSS JOIN `ville` as v2\n" + 
				"    WHERE v1.nom=:depart AND v2.nom=:arrivee");
		
		q.setParameter("depart", depart);
		q.setParameter("arrivee", arrivee);
		try {
			Object[] result = (Object[])q.getSingleResult();
			return Optional.of(new Trajet((String)result[0], (String)result[1], (Double)result[2]));
		}
		catch (NoResultException ex) {
			return Optional.empty();
		}
		
	}

}
