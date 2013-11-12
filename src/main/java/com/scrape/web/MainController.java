package com.scrape.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.scrape.dao.DataBaseHelper;
import com.scrape.model.Event;

@Controller
@RequestMapping("/scrap")
public class MainController {
    
	private static Logger log = Logger.getLogger(MainController.class);

	@Autowired
	DataBaseHelper dataBaseHelper;
	
	@RequestMapping("/statusform.htm")
	public String getStatusForm(HttpServletRequest request, Model model) throws ServletException {
		log.info("getStatusForm :: Begin");
		dataBaseHelper.insertAllEvents();
		List<Event> eventList = dataBaseHelper.findAllEvents();
		Gson gson = new Gson();
		model.addAttribute("scrapData", gson.toJson(eventList));
		log.info("getStatusForm :: End");
		return "statusform";
	}
}
