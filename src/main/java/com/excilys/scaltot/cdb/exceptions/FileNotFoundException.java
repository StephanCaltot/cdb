package com.excilys.scaltot.cdb.exceptions;

/**
 * @author Caltot Stéphan
 *
 *         27 févr. 2017
 */
public class FileNotFoundException extends RuntimeException {

    /**
    *
    */
    private static final long serialVersionUID = 1L;

/**
    * Empty constructor.
    */
   public FileNotFoundException() {

   }

   /**
    * Constructor with message and throwable.
    * @param message : message
    * @param cause : cause
    */
   public FileNotFoundException(String message, Throwable cause) {
       super(message, cause);
   }

   /**
    * Constructor with message.
    * @param message : message
    */
   public FileNotFoundException(String message) {
       super(message);
   }

   /**
    * Constructor with cause .
    * @param cause : cause
    */
   public FileNotFoundException(Throwable cause) {
       super(cause);
   }
}
