package com.declercq.pieter.datumcontrole.model.exception.db;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 * @see http://codereview.stackexchange.com/questions/103821/productmanager-a-basic-crud-for-products-with-sqlite
 */
public class LocationNotFoundException extends DatabaseException {

    private static final long serialVersionUID = -392489517170101116L;

    public LocationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationNotFoundException(String message) {
        super(message);
    }

    public LocationNotFoundException(Throwable cause) {
        super(cause);
    }

    public LocationNotFoundException() {
        super();
    }

}
