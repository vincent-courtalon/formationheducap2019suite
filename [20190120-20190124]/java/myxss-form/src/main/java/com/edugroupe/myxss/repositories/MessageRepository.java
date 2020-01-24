package com.edugroupe.myxss.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.myxss.metier.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {

}
