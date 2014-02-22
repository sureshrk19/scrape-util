package com.scrape.service;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.scrape.exception.BadRequestException;
import com.scrape.exception.WebAppApplicationException;
import com.scrape.model.Event;
import com.scrape.model.Page;
import com.scrape.model.SearchCriteria;
import com.scrape.model.SearchResponse;

public class JAXREventService {

	private static Logger log = Logger.getLogger(JAXREventService.class);
	
	@Autowired
	private InitEventService initEventService;
	
	@Autowired
	private EventService eventService;
	
	@POST
	@Path("/searchevents")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResponse searchEvents(@Valid @RequestBody SearchCriteria searchCriteria) throws BadRequestException {
		log.info("REST WS :: searchEvents()::Before invoking  Services");
		SearchResponse searchResponse =new SearchResponse();
		int totalRecords = 0;
		int totalPages = 0;
		int rowsPerPage = 15;
		try {
			int pageNo = searchCriteria.getPageNo() > 0 ? searchCriteria.getPageNo() : 0;
			String sortByName = StringUtils.isNotBlank(searchCriteria.getSortByName()) ? searchCriteria.getSortByName() : "source";
			String sortOrder = StringUtils.isNotBlank(searchCriteria.getSortOrder()) ? searchCriteria.getSortOrder() : "asc";

			searchCriteria.setPageNo(pageNo);
			searchCriteria.setPageSize(rowsPerPage);
			searchCriteria.setSortByName(sortByName);
			searchCriteria.setSortOrder(sortOrder);

			// TODO : need to remove (testing purpose)
			initEventService.insertAllEvents();
						
			Page<Event> eventPages = eventService.searchEvents(searchCriteria);

			List<Event> eventList = eventPages.getPageItems();
			totalRecords = eventPages.getTotalNoOfRecords();
			totalPages = (totalRecords % rowsPerPage) > 0 ? ((totalRecords / rowsPerPage) + 1) : (totalRecords / rowsPerPage);

			searchResponse.setEventList(eventList);
			searchResponse.setFromDate(searchCriteria.getFromDate());
			searchResponse.setToDate(searchCriteria.getToDate());
			searchResponse.setLocation(searchCriteria.getLocation());
			searchResponse.setName(searchCriteria.getName());
			searchResponse.setPageNo(pageNo);
			searchResponse.setPageSize(searchCriteria.getPageSize());
			searchResponse.setSource(searchCriteria.getSource());
			searchResponse.setTotalPages(totalPages);
			searchResponse.setTotalRecords(totalRecords);
			
		} catch (WebAppApplicationException e) {
			log.error("searchEvents :: Failed to search event details.", e);
			throw new BadRequestException("system.http.badrequest");
		}
		log.info("REST WS :: searchEvents()::After invoking  Services");
		return searchResponse;
	}
}
