package com.scrape.model;

import java.io.Serializable;

public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pageNo;
	private int pageSize;
	private String sortByName;
	private String sortOrder;
	private String fromDate;
	private String toDate;
	private String name;
	private String location;
	private String type;
	private String source;
	
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
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
		return "SearchCriteria [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", sortByName=" + sortByName + ", sortOrder=" + sortOrder
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", name="
				+ name + ", location=" + location + ", type=" + type
				+ ", source=" + source + "]";
	}
	
}
