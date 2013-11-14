package com.scrape.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class WebAppException extends Exception {
	
	private static final long serialVersionUID = 1L;

	// the nested exception
	private Throwable nestedException_;

	// String representation of stack trace - not transient!
	private String stackTraceString_;

	private long exceptionCode_;

	/**
	 * convert a stack trace to a String so it can be serialized
	 * 
	 * @param throwable
	 * @return String
	 */
	static public String generateStackTraceString(Throwable t) {
		StringWriter s = new StringWriter();
		t.printStackTrace(new PrintWriter(s));
		return s.toString();
	}

	/**
	 * Default Constructor
	 */
	public WebAppException() {
	}

	/**
	 * Additional constructor with msg
	 * 
	 * @param msg
	 */
	public WebAppException(String message) {
		super(message);
	}

	/**
	 * Additional constructor - nest the exceptions, storing the stack trace
	 * 
	 * @param nestedException
	 */
	public WebAppException(Throwable nestedException) {
		this.nestedException_ = nestedException;
		stackTraceString_ = generateStackTraceString(nestedException);
		extractProperties(nestedException);
	}

	/**
	 * Additional constructor - nest the exceptions, storing the stack trace
	 * 
	 * @param message
	 * @param nestedException
	 */
	public WebAppException(String message, Throwable nestedException) {
		this(message);
		this.nestedException_ = nestedException;
		stackTraceString_ = generateStackTraceString(nestedException);
		extractProperties(nestedException);
	}

	/**
	 * gets the NestedException
	 * 
	 * @return NestedException
	 */
	public Throwable getNestedException() {
		return nestedException_;
	}

	/**
	 * gets the LeafException
	 * 
	 * @return LeafException
	 */
	public Throwable getLeafException() {
		if (nestedException_ instanceof WebAppException) {
			return ((WebAppException) nestedException_).getLeafException();
		}
		return nestedException_;
	}

	/**
	 * Descends through linked-list of nesting exceptions, & output trace note
	 * that this displays the 'deepest' trace first
	 * 
	 * @return String
	 */
	public String getStackTraceString() {
		// if there's no nested exception, there's no stackTrace
		if (nestedException_ == null)
			return null;

		StringBuffer traceBuffer = new StringBuffer();

		if (nestedException_ instanceof WebAppException) {

			traceBuffer.append(((WebAppException) nestedException_)
					.getStackTraceString());
			traceBuffer.append("-------- nested by:\n");
		}

		traceBuffer.append(stackTraceString_);
		return traceBuffer.toString();
	}

	/**
	 * Overrides Exception.getMessage()
	 * 
	 * @return String
	 */
	public String getMessage() {
		// superMsg will contain whatever String was passed into the
		// constructor, and null otherwise.
		String superMsg = super.getMessage();

		// if there's no nested exception, do like we would always do
		if (getNestedException() == null)
			return superMsg;

		StringBuffer theMsg = new StringBuffer();

		// get the nested exception's message
		String nestedMsg = getNestedException().getMessage();

		if (superMsg != null)
			theMsg.append(superMsg).append(": ").append(nestedMsg);
		else
			theMsg.append(nestedMsg);

		return theMsg.toString();
	}

	/**
	 * Overrides Exception.toString()
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer theMsg = new StringBuffer(super.toString());

		if (getNestedException() != null)
			theMsg.append("; \n\t---> nested ").append(getNestedException());

		return theMsg.toString();
	}

	/**
	 * Sets the ExceptionCode
	 * 
	 * @param code
	 */
	public void setExceptionCode(String code) {
		this.exceptionCode_ = Long.parseLong(code);
	}

	/**
	 * Sets the ExceptionCode
	 * 
	 * @param code
	 */
	public void setExceptionCode(long code) {
		this.exceptionCode_ = code;
	}

	/**
	 * Gets the ExceptionCode
	 * 
	 * @return String
	 */
	public String getExceptionCode() {
		return String.valueOf(this.exceptionCode_);
	}

	/**
	 * Gets the ExceptionCode as long
	 * 
	 * @return long
	 */
	public long getExceptionCodeAsLong() {
		return this.exceptionCode_;
	}

	/**
	 * Prints the StackTrace
	 */
	public void printStackTrace() {
		super.printStackTrace();
		if (nestedException_ != null) {
			nestedException_.printStackTrace();
		}
	}

	/**
	 * Prints the StackTrace from the PrintStream
	 * 
	 * @param printStream
	 */
	public void printStackTrace(java.io.PrintStream printStream) {
		super.printStackTrace(printStream);
		if (nestedException_ != null) {
			printStream.println("Caused by:");
			nestedException_.printStackTrace(printStream);
		}
	}

	/**
	 * Prints the StackTrace from the PrintWriter
	 * 
	 * @param printWriter
	 */
	public void printStackTrace(java.io.PrintWriter printWriter) {
		super.printStackTrace(printWriter);
		if (nestedException_ != null) {
			printWriter.println("Caused by:");
			nestedException_.printStackTrace(printWriter);
		}
	}

	/**
	 * Extracts the properties of an exception
	 * 
	 * @param throwable
	 */
	private void extractProperties(Throwable throwable) {
		// if instance of ProservException, extract the exception code
		if (throwable instanceof WebAppException) {
			this.setExceptionCode(((WebAppException) throwable)
					.getExceptionCode());
		} // end of if ()
	}
}
