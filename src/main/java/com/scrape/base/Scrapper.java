package com.scrape.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service("scrapper")
public class Scrapper extends BaseScrapper {
	
	private static Logger log = Logger.getLogger(Scrapper.class);
	
	public List<Event> getScapperEventList(Map<String, String> scrapSelectorMap) throws Exception {
		
		log.info("getScapperEventList :: Begin");
		
		String baseUrl = scrapSelectorMap.get("baseurl");
		String listSelector = scrapSelectorMap.get("listselector");

		scrapSelectorMap.remove("baseurl");
		scrapSelectorMap.remove("listselector");
		
		validate(baseUrl, listSelector, scrapSelectorMap);
		String pageHtml = fetchPageHTML(baseUrl);
		Document doc = fetchPageDocument(pageHtml);
		Elements elements = doc.select(listSelector);
		List<Event> eventList = new ArrayList<Event>();
		for (Element element : elements) {
			Event e = new Event();
			for (Map.Entry<String, String> entry : scrapSelectorMap.entrySet()) {
				String value = element.select(entry.getValue()).text();
				Field field = Event.class.getDeclaredField(entry.getKey().substring(entry.getKey().lastIndexOf('.') + 1));
				field.set(e, value);
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
