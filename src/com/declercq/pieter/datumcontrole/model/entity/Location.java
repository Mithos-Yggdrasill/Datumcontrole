package com.declercq.pieter.datumcontrole.model.entity;

import com.declercq.pieter.datumcontrole.model.exception.DomainException;
import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class Location {

    /**
     * The minimum amount of characters the name of a location must have.
     */
    public static final int MIN_AMOUNT_OF_CHARACTERS_NAME = 2;

    /**
     * The maximum amount of characters the name of a location may have.
     */
    public static final int MAX_AMOUNT_OF_CHARACTERS_NAME = 20;

    private String name;

    public Location() {

    }

    Location(String name) throws DomainException {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws DomainException {
        if (name == null) {
            throw new DomainException(ErrorMessages.NAME_NULL);
        }
        name = name.replaceAll(System.getProperty("line.separator"), "");
        name = name.replaceAll("\r|\n", "");
        name = name.toLowerCase().trim();
        if (!name.matches("^[.,a-zA-Z0-9 ]+$")) {
            throw new DomainException(ErrorMessages.NAME_ALPHANUMERIC);
        }
        if (name.length() < MIN_AMOUNT_OF_CHARACTERS_NAME) {
            throw new DomainException(ErrorMessages.PRODUCT_NAME_MIN_LENGTH);
        }
        if (name.length() > MAX_AMOUNT_OF_CHARACTERS_NAME) {
            throw new DomainException(ErrorMessages.PRODUCT_NAME_MAX_LENGTH);
        }
        this.name = name;
    }

}