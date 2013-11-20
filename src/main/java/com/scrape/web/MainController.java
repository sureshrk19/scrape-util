package com.scrape.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.scrape.exception.WebAppApplicationException;
import com.scrape.model.Event;
import com.scrape.model.Page;
import com.scrape.model.SearchCriteria;
import com.scrape.service.EventService;
import com.scrape.service.InitEventService;

@Controller
@RequestMapping("/scrape")
public class MainController {
    
	private static Logger log = Logger.getLogger(MainController.class);

	@Autowired
	private InitEventService initEventService;
	
	@Autowired
	private EventService eventService;
	
	/**
	 * Getting the list of pageable events by given search criteria.
	 * 
	 * @param searchCriteria
	 * @param request
	 * @param model
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/events.htm")
	public String searchEvents(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria, HttpServletRequest request, Model model) throws ServletException {
		log.info("searchEvents :: Begin");
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

			Gson gson = new Gson();
			model.addAttribute("scrapData", gson.toJson(eventList));
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("totalRecords", totalRecords);
			model.addAttribute("pageNo", pageNo);


			model.addAttribute("name", searchCriteria.getName());
			model.addAttribute("location", searchCriteria.getLocation());		
			model.addAttribute("type", searchCriteria.getType());
			model.addAttribute("fromDate", searchCriteria.getFromDate());
			model.addAttribute("toDate", searchCriteria.getToDate());
			model.addAttribute("source", searchCriteria.getSource());

		} catch (WebAppApplicationException e) {
			log.error("searchEvents :: Failed to getting events", e);
			throw new ServletException();
		}
		
		log.info("searchEvents :: End");
		return "statusform";
	}
	
}
