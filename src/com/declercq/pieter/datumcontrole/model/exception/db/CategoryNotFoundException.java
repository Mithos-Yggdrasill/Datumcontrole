package com.declercq.pieter.datumcontrole.model.exception.db;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 * @see http://codereview.stackexchange.com/questions/103821/productmanager-a-basic-crud-for-products-with-sqlite
 */
public class CategoryNotFoundException extends DatabaseException {

    private static final long serialVersionUID = -392489517170101116L;

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }

    public CategoryNotFoundException() {
        super();
    }

}
