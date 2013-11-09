package com.scrape.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

@Service("scrapperClient")
public class ScrapperClient {

	private static Logger log = Logger.getLogger(ScrapperClient.class);

	@Autowired
	Scrapper scrapper;
	
	public List<Event> getScrapperData() throws Exception {

		log.info("getScrapeData :: Begin");
		
		List<Event> eventList = new ArrayList<Event>();
		
		Properties prop = getConfigProperties();

		Map<String, String> propMap = getPropertiesMap(prop);
		
		Map<String, Map<String, String>> propGroupMap = getGroupPropertiesMap(propMap);
		
		for (Map.Entry<String, Map<String, String>> propGroupEntry : propGroupMap.entrySet()) {
			Map<String, String> scrapSelectorMap = propGroupMap.get(propGroupEntry.getKey());
			eventList = scrapper.getScapperEventList(scrapSelectorMap);
		}
		log.info("getScrapeData :: End");
		return eventList;
	}

	private static Map<String, Map<String, String>> getGroupPropertiesMap(Map<String, String> propMap) {
		Map<String, Map<String, String>> propGroupMap = new HashMap<String, Map<String, String>>();
		for (Map.Entry<String, String> entry : propMap.entrySet()) {
			String givenKey = entry.getKey();
			String givenValue = entry.getValue();
			String[] splittedKey = givenKey.split("\\.");
			String newOuterKey = splittedKey[0];
			String newInnerKey = splittedKey[1];
			if (!propGroupMap.containsKey(newOuterKey)) {
				propGroupMap.put(newOuterKey, new HashMap<String, String>());
			}
			propGroupMap.get(newOuterKey).put(newInnerKey, givenValue);
		}
		return propGroupMap;
	}
	
	private static Map<String, String> getPropertiesMap(Properties prop) {
		Map<String, String> propMap = new HashMap<String, String>();
		for (Entry<Object, Object> entry : prop.entrySet()) {
			propMap.put((String) entry.getKey(),
					(String) entry.getValue());
		}
		return propMap;
	}

	private static Properties getConfigProperties() {
		Properties prop = new Properties();
		try {
			prop = PropertiesLoaderUtils.loadAllProperties("scrapper.properties");
		} catch (IOException ex) {
			log.error("insertAllEvents :: IOException ::" + ex.getMessage());
		}
		return prop;
	}
}
