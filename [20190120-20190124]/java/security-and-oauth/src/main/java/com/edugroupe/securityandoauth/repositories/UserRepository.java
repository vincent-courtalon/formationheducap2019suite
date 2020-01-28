package com.edugroupe.securityandoauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edugroupe.securityandoauth.metier.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
	
}
