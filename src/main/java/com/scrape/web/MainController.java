package com.scrape.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.scrape.model.Event;
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
	
	@RequestMapping("/events.htm")
	public String getEvents(HttpServletRequest request, Model model) throws ServletException {
		log.info("getEvents :: Begin");
		long totalRecords = 0;
		int totalPages = 0;
		int rowsPerPage = 15;
		SearchCriteria searchCriteria = new SearchCriteria();
		int pageNo = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 0;
		String sortByName = request.getParameter("sort") != null ? request.getParameter("sort") : "source";
		String sortOrder = request.getParameter("direction") != null ? request.getParameter("direction") : "asc";
		
		searchCriteria.setPageNo(pageNo);
		searchCriteria.setPageSize(rowsPerPage);
		searchCriteria.setSortByName(sortByName);
		searchCriteria.setSortOrder(sortOrder);
		
		// TODO : need to remove (testing purpose)
		initEventService.insertAllEvents();
		
		Page<Event> eventPages = eventService.getEvents(searchCriteria);
		List<Event> eventList = eventPages.getContent();
		totalRecords = eventPages.getTotalElements();
		totalPages = eventPages.getTotalPages();
		
		Gson gson = new Gson();
		model.addAttribute("scrapData", gson.toJson(eventList));
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalRecords", totalRecords);
		model.addAttribute("pageNo", pageNo);
		
		log.info("getEvents :: End");
		return "statusform";
	}
	
	@RequestMapping("/searchevents.htm")
	public String searchEvents(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria, HttpServletRequest request, Model model) throws ServletException {
		log.info("getEvents :: Begin");
		long totalRecords = 0;
		int totalPages = 0;
		int rowsPerPage = 15;
		int pageNo = searchCriteria.getPageNo() > 0 ? searchCriteria.getPageNo() : 0;
		String sortByName = searchCriteria.getSortByName() != null ? searchCriteria.getSortByName() : "source";
		String sortOrder = searchCriteria.getSortOrder() != null ? searchCriteria.getSortOrder() : "asc";
		
		searchCriteria.setPageNo(pageNo);
		searchCriteria.setPageSize(rowsPerPage);
		searchCriteria.setSortByName(sortByName);
		searchCriteria.setSortOrder(sortOrder);
		
		Page<Event> eventPages = eventService.getEvents(searchCriteria);
		List<Event> eventList = eventPages.getContent();
		totalRecords = eventPages.getTotalElements();
		totalPages = eventPages.getTotalPages();
		
		Gson gson = new Gson();
		model.addAttribute("scrapData", gson.toJson(eventList));
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalRecords", totalRecords);
		model.addAttribute("pageNo", pageNo);
		
		log.info("getEvents :: End");
		return "statusform";
	}
	
	@RequestMapping("/statusform.htm")
	public String getStatusForm(HttpServletRequest request, Model model) throws ServletException {
		log.info("getStatusForm :: Begin");
		initEventService.insertAllEvents();
		List<Event> eventList = eventService.findAllEvents();
		Gson gson = new Gson();
		model.addAttribute("scrapData", gson.toJson(eventList));
		log.info("getStatusForm :: End");
		return "statusform";
	}
}
