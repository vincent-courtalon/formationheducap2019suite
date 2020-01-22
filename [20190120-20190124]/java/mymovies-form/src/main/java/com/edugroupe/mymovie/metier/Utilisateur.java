package com.edugroupe.mymovie.metier;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Utilisateur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;
	@Column(length = 100, unique = true, nullable = false) 	private String login;
	@JsonIgnore @Column(length= 100, nullable = false)		private String password;
															private boolean enabled;
	@ManyToMany /*(fetch = FetchType.EAGER)*/				private Set<Role> roles;
	

}
