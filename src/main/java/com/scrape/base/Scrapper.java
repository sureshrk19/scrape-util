package com.scrape.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
public class Scrapper extends BaseScrapper{
	
	private Map<String, String> keySelectorMap;
	private String baseUrl;
	private String listSelector;

	// TODO: Extract Model
	public Scrapper(String baseUrl, String listSelector, Map<String, String> keySelectorMap) {
		this.keySelectorMap = keySelectorMap;
		this.baseUrl=baseUrl;
		this.listSelector=listSelector;
	}
	
	public String parse() throws Exception {
		
		validate();
		String pageHtml=this.fetchPageHTML(baseUrl);
		Document doc= this.fetchPageDocument(pageHtml);
		Elements elements = doc.select(listSelector);
		java.util.List<Event> eventList = new ArrayList<Event>();
		for (Element element : elements) {
			Event e=new Event();
			for (Map.Entry<String, String> entry : keySelectorMap.entrySet()) {
				String value=element.select(entry.getValue()).text();
				Field field = Event.class.getDeclaredField(entry.getKey());
				field.set(e, value);
			}
			eventList.add(e);
		}
		Gson gson = new Gson();
		return gson.toJson(eventList);
	}

	private void validate() throws Exception {
		if(this.keySelectorMap.size() == 0){
			throw new Exception("keySelectorMap cannot be empty");
		}
		if(this.listSelector.trim().length()==0){
			throw new Exception("listSelector cannot be empty");
		}
		if(this.baseUrl.trim().length()==0){
			throw new Exception("baseUrl cannot be empty");
		}
	}
}


