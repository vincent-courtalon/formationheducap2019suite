package com.edugroupe.firstsecurity.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.firstsecurity.metier.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

}
