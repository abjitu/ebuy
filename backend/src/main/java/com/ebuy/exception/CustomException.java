package com.ebuy.exception;


/**
 * The Class CustomException.
 */
public class CustomException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new custom exception.
     *
     * @param message the message
     */
    public CustomException(String message) {
        super(message);
    }

    /**
     * Instantiates a new custom exception.
     *
     * @param message the message
     * @param t the t
     */
    public CustomException(String message, Throwable t) {
        super(message, t);
    }

}
