package com.scrape.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.scrape.model.Event;
import com.scrape.service.ScrapperClient;

@Component
public class DataBaseHelper {

	private static Logger log = Logger.getLogger(DataBaseHelper.class);

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	ScrapperClient scrapperClient;

	public void insertAllEvents() {

		log.info("insertAllEvents :: Begin.");

		if (mongoOperations.collectionExists(Event.class)) {
			mongoOperations.dropCollection(Event.class);
		}
		mongoOperations.createCollection(Event.class);

		try {
			List<Event> eventList = scrapperClient.getScrapperData();
			mongoOperations.insert(eventList, Event.class);
		} catch (Exception e) {
			log.info("Exception ::"+e.getMessage());
		}
		log.info("insertAllEvents :: End!");
	}

	public List<Event> findAllEvents() {
		log.info("findAllEvents :: Begin");
		List<Event> results = new ArrayList<Event>();
		try {
			results = mongoOperations.findAll(Event.class);
		} catch (Exception e) {
			log.info("Exception ::"+e.getMessage());
		}
		log.info("findAllEvents :: End!");
		return results;
	}
}
