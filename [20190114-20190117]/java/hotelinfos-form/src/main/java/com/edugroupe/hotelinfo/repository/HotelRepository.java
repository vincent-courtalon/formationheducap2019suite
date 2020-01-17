package com.edugroupe.hotelinfo.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.hotelinfo.metier.Hotel;

public interface HotelRepository extends PagingAndSortingRepository<Hotel, Integer> {

	Optional<Hotel> findByVille(String ville); 
}
