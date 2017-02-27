package com.excilys.scaltot.cdb.exceptions;

public class PersistenceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Empty constructor.
     */
    public PersistenceException() {

    }

    /**
     * Constructor with message and throwable.
     * @param message : message
     * @param cause : cause
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message.
     * @param message : message
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * Constructor with cause .
     * @param cause : cause
     */
    public PersistenceException(Throwable cause) {
        super(cause);
    }

}
