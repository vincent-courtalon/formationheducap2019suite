package com.edugroupe.livreinfos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.livreinfos.metier.Livre;

public interface LivreRepository extends PagingAndSortingRepository<Livre, Integer> {

	Optional<Livre> findByIsbn(String isbn);
}
