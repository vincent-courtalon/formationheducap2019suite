package com.edugroupe.springswagger2form.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springswagger2form.metier.Zoo;

public interface ZooRepository extends PagingAndSortingRepository<Zoo, Integer> {

}
