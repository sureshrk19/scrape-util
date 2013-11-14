package com.scrape.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrape.exception.WebAppApplicationException;
import com.scrape.model.Event;

@Component
@Transactional
public class InitEventService {

	private static Logger log = Logger.getLogger(InitEventService.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	ScrapperClient scrapperClient;

	public void insertAllEvents() throws WebAppApplicationException {

		log.info("insertAllEvents :: Begin.");

		if (mongoTemplate.collectionExists(Event.class)) {
			mongoTemplate.dropCollection(Event.class);
		}
		mongoTemplate.createCollection(Event.class);

		try {
			List<Event> eventList = scrapperClient.getScrapperData();
			mongoTemplate.insert(eventList, Event.class);
		} catch (Exception e) {
			log.info("Exception ::" + e.getMessage());
			throw new WebAppApplicationException(e);
		}
		log.info("insertAllEvents :: End!");
	}

}
