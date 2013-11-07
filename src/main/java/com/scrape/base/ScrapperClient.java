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
	
	public List<Event> getScrapeData () throws Exception {
		
		Properties prop=readConfigFile("nfl.properties");
		
		String baseUrl = prop.getProperty("baseurl");
        String listSelector = prop.getProperty("listselector");
        
		removeAllExceptActualProperties(prop);
		Map<String, String>keySelectorMap = getKeySelectorMap(prop);
		
		Scrapper scrapper = new Scrapper(baseUrl,listSelector,keySelectorMap);
		List<Event> eventList = scrapper.parse();
		return eventList;
	}
	
	private static Map<String, String> getKeySelectorMap(Properties prop) {
		Map<String, String>keySelectorMap = new HashMap<String, String>();
		for (Entry<Object, Object> entry : prop.entrySet()) {
			keySelectorMap.put((String)entry.getKey(),(String)entry.getValue());	
		}
		return keySelectorMap;
	}
	
	private static void removeAllExceptActualProperties(Properties prop) {
		prop.remove("baseurl");
		prop.remove("listselector");
	}
	
	static Properties readConfigFile(String fileName){
		Properties prop = new Properties();
		try {
			prop = PropertiesLoaderUtils.loadAllProperties("nfl.properties");
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    	return prop;
	}
}
