package com.edugroupe.mybooksform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.mybooksform.metier.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

}
