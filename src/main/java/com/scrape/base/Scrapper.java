package com.scrape.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Scrapper extends BaseScrapper{
	
	private static Logger log = Logger.getLogger(Scrapper.class);
	
	private String baseUrl;
	private String listSelector;
	private Map<String, String> keySelectorMap;

	public Scrapper(String baseUrl, String listSelector, Map<String, String> keySelectorMap) {
		this.keySelectorMap = keySelectorMap;
		this.baseUrl = baseUrl;
		this.listSelector = listSelector;
	}
	
	public List<Event> parse() throws Exception {
		
		validate();
		String pageHtml = fetchPageHTML(baseUrl);
		Document doc = fetchPageDocument(pageHtml);
		Elements elements = doc.select(listSelector);
		List<Event> eventList = new ArrayList<Event>();
		for (Element element : elements) {
			Event e = new Event();
			for (Map.Entry<String, String> entry : keySelectorMap.entrySet()) {
				String value = element.select(entry.getValue()).text();
				Field field = Event.class.getDeclaredField(entry.getKey().substring(entry.getKey().lastIndexOf('.') + 1));
				field.set(e, value);
			}
			eventList.add(e);
		}
		return eventList;
	}

	private void validate() throws Exception {
		if(this.keySelectorMap.size() == 0){
			log.error("keySelectorMap cannot be empty");
		}
		
		if(this.listSelector.trim().length()==0){
			log.error("listSelector cannot be empty");
		}
		
		if(this.baseUrl.trim().length()==0){
			log.error("baseUrl cannot be empty");
		}
	}
}


