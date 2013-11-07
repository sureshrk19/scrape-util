package com.scrape.base;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Event {
	
	String week;
	String date;
	String time;
	String name;
	
	
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	
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
	
	@Override
	public String toString() {
		return "Event [week=" + week + ", date=" + date + ", time=" + time
				+ ", name=" + name + "]";
	}	
	
}
