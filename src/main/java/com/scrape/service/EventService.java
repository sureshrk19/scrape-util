package com.scrape.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scrape.model.Event;
import com.scrape.model.SearchCriteria;
import com.scrape.repository.IEventRepository;

@Repository("eventService")
@Transactional
public class EventService {

	private static Logger log = Logger.getLogger(EventService.class);
	
	@Autowired
	private IEventRepository eventRepository;
	
	public List<Event> findAllEvents() {
		log.info("findAllEvents :: Begin");
		List<Event> results = new ArrayList<Event>();
		try {
			results = eventRepository.findAll();
		} catch (Exception e) {
			log.info("Exception ::" + e.getMessage());
		}
		log.info("findAllEvents :: End!");
		return results;
	}
	
	public Page<Event> getEvents(SearchCriteria reviewCriteria) {
		log.info("getEvents :: Begin");
		Page<Event> results = null;
		try {
			results = eventRepository.findAll(new PageRequest(reviewCriteria.getPageNo(), reviewCriteria.getPageSize()));
		} catch (Exception e) {
			log.info("Exception ::" + e.getMessage());
		}
		log.info("getEvents :: End!");
		return results;
	}
	
	
}
