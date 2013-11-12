package com.scrape.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BaseScrapper {
	public String fetchPageHTML(String url) throws Exception{
		if(url.length() == 0){
			throw new Exception("No url specified for scrapping");
		}
		return Jsoup.connect(url).execute().body();
	}
	public Document fetchPageDocument(String html) throws Exception{
		if(html.trim().length() == 0){
			throw new Exception("Blank page returned from the server");
		}
		return Jsoup.parse(html);
	}
}
