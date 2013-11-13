package com.scrape.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.scrape.model.Event;
import com.scrape.model.SearchCriteria;
import com.scrape.service.EventService;
import com.scrape.service.InitEventService;

@Controller
@RequestMapping("/scrap")
public class MainController {
    
	private static Logger log = Logger.getLogger(MainController.class);

	@Autowired
	private InitEventService initEventService;
	
	@Autowired
	private EventService eventService;
	
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
	
	@RequestMapping("/events.htm")
	public String getEvents(HttpServletRequest request, Model model) throws ServletException {
		log.info("getEvents :: Begin");
		long totalRecords = 0;
		int totalPages = 0;
		int rowsPerPage = 20;
		int pageNo = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		SearchCriteria reviewCriteria = new SearchCriteria();
		reviewCriteria.setPageNo(pageNo);
		reviewCriteria.setPageSize(rowsPerPage);
		
		Page<Event> eventPages = eventService.getEvents(reviewCriteria);
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
}
