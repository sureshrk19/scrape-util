package com.scrape.exception;

public class WebAppApplicationException extends WebAppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public WebAppApplicationException() {
	}

	/**
	 * Additional Constructor - Create exception wrapping another one
	 * 
	 * @param exception
	 */
	public WebAppApplicationException(Exception exception) {
		super(exception);
	}

	/**
	 * Additional Constructor - Create exception with a message
	 * 
	 * @param string
	 */
	public WebAppApplicationException(String string) {
		super(string);
	}

	/**
	 * Create a exception with message and source exception
	 * 
	 * @param string
	 * @param throwable
	 */
	public WebAppApplicationException(String str, Throwable e) {
		super(str, e);
	}
}
