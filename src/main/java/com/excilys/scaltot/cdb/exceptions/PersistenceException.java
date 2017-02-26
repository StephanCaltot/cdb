package com.excilys.scaltot.cdb.exceptions;

public class PersistenceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public PersistenceException(){
		
	}
	
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
	public PersistenceException(String message) {
		super(message);
	}
	
	public PersistenceException(Throwable cause) {
		super(cause);
	}
	
}
