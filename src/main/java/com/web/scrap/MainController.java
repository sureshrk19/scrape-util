package com.web.scrap;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.scrape.base.Event;
import com.scrape.db.DataBaseHelper;

@Controller
@RequestMapping("/scrap")
public class MainController {
    
	private static Logger log = Logger.getLogger(MainController.class);

	@Autowired
	DataBaseHelper dataBaseHelper;
	
	@RequestMapping("/statusform.htm")
	public String getStatusForm(HttpServletRequest request, Model model) throws ServletException {
		log.info("getStatusForm :: Begin");
		HttpSession session = request.getSession(true);
		//dataBaseHelper.insertAllEvents();
		List<Event> eventList = dataBaseHelper.findAllEvents();
		 Gson gson = new Gson();
		session.setAttribute("scrapData",gson.toJson(eventList));
		log.info("getStatusForm :: End");
		return "statusform";
	}
}
