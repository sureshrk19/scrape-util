package com.scrape.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.scrape.model.Event;

@Transactional
public interface IEventRepository extends MongoRepository<Event, String> {
	
}
