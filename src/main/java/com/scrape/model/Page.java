package com.scrape.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Page Bean Class - This class is used to retrieve the objects from database when pagination is opted.
 * @author sriram
 *
 * @param <E>
 */
public class Page<E> {

	private int pageNumber;
	private int totalNoOfRecords;
	private List<E> pageItems = new ArrayList<E>();

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setTotalNoOfRecords(int pagesAvailable) {
		this.totalNoOfRecords = pagesAvailable;
	}

	public void setPageItems(List<E> pageItems) {
		this.pageItems = pageItems;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getTotalNoOfRecords() {
		return totalNoOfRecords;
	}
	
	public List<E> getPageItems() {
		return pageItems;
	}
}
