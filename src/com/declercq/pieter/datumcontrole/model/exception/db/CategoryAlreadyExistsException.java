package com.declercq.pieter.datumcontrole.model.exception.db;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 * @see http://codereview.stackexchange.com/questions/103821/productmanager-a-basic-crud-for-products-with-sqlite
 */
public class CategoryAlreadyExistsException extends DatabaseException {

    private static final long serialVersionUID = -392489517170101116L;

    public CategoryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }

    public CategoryAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public CategoryAlreadyExistsException() {
        super();
    }

}
