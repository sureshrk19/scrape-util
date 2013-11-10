package com.scrape.base;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Event {
	
	String date;
	String time = "";
	String name;
	String location;
	String type;
	String source;
	
	public String getDate() {
		return date + getTime();
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
	public String toString() {
		return "Event [date=" + date + ", time=" + time + ", name=" + name
				+ ", location=" + location + ", type=" + type + ", source="
				+ source + "]";
	}
	
}
