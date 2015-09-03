package com.declercq.pieter.datumcontrole.model.exception;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = -392489517170101125L;
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException() {
        super();
    }
    
}
