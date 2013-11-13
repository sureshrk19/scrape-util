package com.scrape.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String date;
	private String time;
	private String name;
	private String location;
	private String type;
	private String source;
	
	public String getDate() {
		return date;
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
