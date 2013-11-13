package com.scrape.model;

import java.io.Serializable;

public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pageNo;
	private int pageSize;
	private String sortByName;
	private String sortOrder;
	private String date;
	private String name;
	private String location;
	private String type;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSortByName() {
		return sortByName;
	}
	public void setSortByName(String sortByName) {
		this.sortByName = sortByName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	@Override
	public String toString() {
		return "SearchCriteria [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", sortByName=" + sortByName + ", sortOrder=" + sortOrder
				+ ", date=" + date + ", name=" + name + ", location="
				+ location + ", type=" + type + "]";
	}
	
}
