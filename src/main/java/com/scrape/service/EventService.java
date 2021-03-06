package com.scrape.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scrape.exception.WebAppApplicationException;
import com.scrape.model.Event;
import com.scrape.model.Page;
import com.scrape.model.SearchCriteria;

@Component
@Transactional
public class EventService {

	private static Logger log = Logger.getLogger(EventService.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * Getting the list of pageable events by given search criteria.
	 * 
	 * @param searchCriteria
	 * @return Page<Event>
	 */
	public Page<Event> searchEvents(SearchCriteria searchCriteria) throws WebAppApplicationException {
		log.info("searchEvents :: Begin");
		Page<Event> eventResults = new Page<Event>();
		try {

			Query query = getQueryCriteria(searchCriteria);
			Pageable pageRequest = new PageRequest(searchCriteria.getPageNo(), searchCriteria.getPageSize());

			int eventTotalCount = (int) mongoTemplate.count(query, Event.class);
			eventResults.setTotalNoOfRecords(eventTotalCount);
			eventResults.setPageNumber(searchCriteria.getPageNo());

			query.with(pageRequest);
			List<Event> eventList = mongoTemplate.find(query, Event.class);
			eventResults.setPageItems(eventList);

		} catch (Exception e) {
			log.error("Exception ::" + e.getMessage());
			throw new WebAppApplicationException(e);
		}
		log.info("searchEvents :: End!");
		return eventResults;
	}
	
	/**
	 * Building query criteria object by given search criteria
	 * 
	 * @param searchCriteria
	 * @return
	 */
	private Query getQueryCriteria(SearchCriteria searchCriteria) throws WebAppApplicationException {
		log.info("getQueryCriteria :: Begin");
		Query query = new Query();
		Sort sort = null;
		boolean isWhere = false;
		Criteria criteria = new Criteria();
		
		try {

			if (StringUtils.isNotBlank(searchCriteria.getName())) {
				criteria = Criteria.where("name").is(searchCriteria.getName());
				isWhere = true;
			}

			if (StringUtils.isNotBlank(searchCriteria.getType())) {
				if (isWhere) {
					criteria = criteria.and("type").is(searchCriteria.getType());
				} else {
					criteria = Criteria.where("type").is(searchCriteria.getType());
					isWhere = true;
				}
			}

			if (StringUtils.isNotBlank(searchCriteria.getFromDate())) {
				if (isWhere) {
					if (StringUtils.isNotBlank(searchCriteria.getToDate())) {
						criteria = criteria.and("date").gte(searchCriteria.getFromDate()).lt(searchCriteria.getToDate());
					} else {
						criteria = criteria.and("date").is(searchCriteria.getFromDate());
					}
				} else {
					if (StringUtils.isNotBlank(searchCriteria.getToDate())) {
						criteria = Criteria.where("date").gte(searchCriteria.getFromDate()).lt(searchCriteria.getToDate());
					} else {
						criteria = Criteria.where("date").is(searchCriteria.getFromDate());
					}
					isWhere = true;
				}
			}
			
			if (StringUtils.isNotBlank(searchCriteria.getLocation())) {
				if (isWhere) {
					criteria = criteria.and("location").is(searchCriteria.getLocation());
				} else {
					criteria = Criteria.where("location").is(searchCriteria.getLocation());
					isWhere = true;
				}
			}
			
			if (StringUtils.isNotBlank(searchCriteria.getSource())) {
				if (isWhere) {
					criteria = criteria.and("source").is(searchCriteria.getSource());
				} else {
					criteria = Criteria.where("source").is(searchCriteria.getSource());
					isWhere = true;
				}
			}
			
			query.addCriteria(criteria);

			if (searchCriteria.getSortOrder().equalsIgnoreCase("asc")) {
				sort = new Sort(Direction.ASC, searchCriteria.getSortByName());
			} else {
				sort = new Sort(Direction.DESC, searchCriteria.getSortByName());
			}
			query.with(sort);
			
		} catch (Exception e) {
			log.info("Exception ::" + e.getMessage());
			throw new WebAppApplicationException(e);
		}
		log.info("getQueryCriteria :: End!");
		return query;
	}
}
