package com.bwf.coursesys.exception;

public class ExcelEception extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8528222012878716167L;

	public ExcelEception() {
		super();
	}

	public ExcelEception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExcelEception(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelEception(String message) {
		super(message);
	}

	public ExcelEception(Throwable cause) {
		super(cause);
	}

}
