package com.web.scrap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scrap")
public class MainController {
    
	private static Logger log = Logger.getLogger(MainController.class);

	@RequestMapping("/statusform.htm")
	public String getStatusForm(HttpServletRequest request, Model model) throws ServletException {
		log.info("getStatusForm :: Begin");
		
		log.info("getStatusForm :: End");
		return "statusform";
	}
}
