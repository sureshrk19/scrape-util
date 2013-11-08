package com.scrape.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

@Service("scrapperClient")
public class ScrapperClient {

	private static Logger log = Logger.getLogger(ScrapperClient.class);

	public List<Event> getScrapeData() throws Exception {

		Properties prop = getConfigProperties();

		String baseUrl = prop.getProperty("nfl.baseurl");
		String listSelector = prop.getProperty("nfl.listselector");

		removeAllExceptActualProperties(prop);
		Map<String, String> keySelectorMap = getKeySelectorMap(prop);

		Scrapper scrapper = new Scrapper(baseUrl, listSelector, keySelectorMap);
		List<Event> eventList = scrapper.parse();
		return eventList;
	}

	private static Map<String, String> getKeySelectorMap(Properties prop) {
		Map<String, String> keySelectorMap = new HashMap<String, String>();
		for (Entry<Object, Object> entry : prop.entrySet()) {
			keySelectorMap.put((String) entry.getKey(),
					(String) entry.getValue());
		}
		return keySelectorMap;
	}

	private static void removeAllExceptActualProperties(Properties prop) {
		prop.remove("nfl.baseurl");
		prop.remove("nfl.listselector");
	}

	static Properties getConfigProperties() {
		Properties prop = new Properties();
		try {
			prop = PropertiesLoaderUtils.loadAllProperties("scrapper.properties");
		} catch (IOException ex) {
			log.error("insertAllEvents :: IOException ::" + ex.getMessage());
		}
		return prop;
	}
}
