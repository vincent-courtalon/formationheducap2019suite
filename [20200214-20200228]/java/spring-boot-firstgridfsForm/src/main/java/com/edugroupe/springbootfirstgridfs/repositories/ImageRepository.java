package com.edugroupe.springbootfirstgridfs.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springbootfirstgridfs.metier.Image;

public interface ImageRepository extends PagingAndSortingRepository<Image, Integer> {

}
