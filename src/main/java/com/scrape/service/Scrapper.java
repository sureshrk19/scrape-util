package com.scrape.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.scrape.model.Event;

@Service("scrapper")
public class Scrapper extends BaseScrapper {
	
	private static Logger log = Logger.getLogger(Scrapper.class);
	
	public List<Event> getScapperEventList(Map<String, String> scrapSelectorMap) throws Exception {
		
		log.info("getScapperEventList :: Begin :: "+scrapSelectorMap.get("baseurl"));
		
		String baseUrl = scrapSelectorMap.get("baseurl");
		String listSelector = scrapSelectorMap.get("listselector");
		String defaultType = scrapSelectorMap.get("defaulttype");

		scrapSelectorMap.remove("baseurl");
		scrapSelectorMap.remove("listselector");
		scrapSelectorMap.remove("defaulttype");
		
		validate(baseUrl, listSelector, scrapSelectorMap);
		String pageHtml = fetchPageHTML(baseUrl);
		Document doc = fetchPageDocument(pageHtml);
		Elements elements = doc.select(listSelector);
		List<Event> eventList = new ArrayList<Event>();
		for (Element element : elements) {
			Event e = new Event();
			e.setSource(baseUrl);
			for (Map.Entry<String, String> entry : scrapSelectorMap.entrySet()) {
				String value = element.select(entry.getValue()).text();
				Field field = Event.class.getDeclaredField(entry.getKey().substring(entry.getKey().lastIndexOf('.') + 1));
				field.setAccessible(true);
				field.set(e, value);
				if(e.getType() == null || e.getType().isEmpty() ) {
					e.setType(defaultType);
				}
			}
			eventList.add(e);
		}
		log.info("getScapperEventList :: End");
		return eventList;
	}

	private void validate(String baseUrl, String listSelector, Map<String, String> scrapSelectorMap) throws Exception {
		
		if(baseUrl.trim().length()==0){
			log.error("baseUrl cannot be empty");
		}
		if(listSelector.trim().length()==0){
			log.error("listSelector cannot be empty");
		}
		if(scrapSelectorMap.isEmpty()){
			log.error("keySelectorMap cannot be empty");
		}
	}
}
