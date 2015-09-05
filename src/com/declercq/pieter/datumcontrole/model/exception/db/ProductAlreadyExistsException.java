package com.declercq.pieter.datumcontrole.model.exception.db;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 * @see
 * http://codereview.stackexchange.com/questions/103821/productmanager-a-basic-crud-for-products-with-sqlite
 */
public class ProductAlreadyExistsException extends DatabaseException {

    private static final long serialVersionUID = -392489517170101116L;

    public ProductAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    public ProductAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ProductAlreadyExistsException() {
        super();
    }

}
