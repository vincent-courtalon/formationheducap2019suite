package com.edugroupe.mymovie.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.mymovie.metier.Role;


public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

}
