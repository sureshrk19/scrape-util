package com.scrape.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrape.model.Event;
import com.scrape.model.SearchCriteria;
import com.scrape.repository.IEventRepository;

@Component
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
	
	
	public Page<Event> getEvents(SearchCriteria searchCriteria) {
		log.info("getEvents :: Begin");
		Page<Event> results = null;
		try {
			
			Sort sort = null;
			if(searchCriteria.getSortOrder().equalsIgnoreCase("asc")) {
				sort = new Sort(Direction.ASC, searchCriteria.getSortByName());
			} else {
				sort = new Sort(Direction.DESC, searchCriteria.getSortByName());
			}
			Pageable  pageRequest = new PageRequest(searchCriteria.getPageNo(), searchCriteria.getPageSize(), sort);
			results = eventRepository.findAll(pageRequest);
		} catch (Exception e) {
			log.info("Exception ::" + e.getMessage());
		}
		log.info("getEvents :: End!");
		return results;
	}
	
	
}
