package com.scrape.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;


public class ScrapperClient {
	
	public static void main(String[] args) throws Exception {
		Properties prop=readConfigFile("nfl.properties");
		
		String baseUrl = prop.getProperty("baseurl");
		String listSelector = prop.getProperty("listselector");
		
		removeAllExceptActualProperties(prop);
		Map<String, String>keySelectorMap=getKeySelectorMap(prop);
		
		Scrapper scrapper = new Scrapper(baseUrl,listSelector,keySelectorMap);
		String jsonString = scrapper.parse();
		System.out.println(jsonString);
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
    		prop.load(new FileInputStream("config/"+fileName));
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    	return prop;
		
	}
}
