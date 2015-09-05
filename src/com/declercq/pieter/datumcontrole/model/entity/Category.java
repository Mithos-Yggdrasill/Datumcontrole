package com.declercq.pieter.datumcontrole.model.entity;

import com.declercq.pieter.datumcontrole.model.exception.DomainException;
import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class Category extends Identifier {

    /**
     * The minimum amount of characters the name of a product must have.
     */
    public static final int MIN_AMOUNT_OF_CHARACTERS_NAME = 2;

    /**
     * The maximum amount of characters the name of a product may have.
     */
    public static final int MAX_AMOUNT_OF_CHARACTERS_NAME = 20;

    /**
     * The minimum amount of sublocations a category must have.
     */
    public static final int MIN_AMOUNT_OF_SUBLOCATIONS = 1;

    /**
     * The maximum amount of sublocations a category may have.
     */
    public static final int MAX_AMOUNT_OF_SUBLOCATIONS = 20;
    
    private String name;

    private int sublocations;

    private String color;

    public Category() {

    }

    public Category(String name, int sublocations, String color) throws DomainException {
        setName(name);
        setSublocations(sublocations);
        setColor(color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws DomainException {
        if(name == null){
            throw new DomainException(ErrorMessages.NAME_NULL);
        }
        name = name.replaceAll(System.getProperty("line.separator"), "");
        name = name.replaceAll("\r|\n", "");
        name = name.toLowerCase().trim();
        if (!name.matches("^[.,a-zA-Z0-9 ]+$")) {
            throw new DomainException(ErrorMessages.NAME_ALPHANUMERIC);
        }
        if (name.length() < MIN_AMOUNT_OF_CHARACTERS_NAME) {
            throw new DomainException(ErrorMessages.CATEGORY_NAME_MIN_LENGTH);
        }
        if (name.length() > MAX_AMOUNT_OF_CHARACTERS_NAME) {
            throw new DomainException(ErrorMessages.CATEGORY_NAME_MAX_LENGTH);
        }
        this.name = name;
    }

    public int getSublocations() {
        return sublocations;
    }

    public void setSublocations(int sublocations) throws DomainException {
        if (sublocations < MIN_AMOUNT_OF_SUBLOCATIONS) {
            throw new DomainException(ErrorMessages.PRODUCT_HOPE_MIN_LENGTH);
        }
        if (sublocations > MAX_AMOUNT_OF_SUBLOCATIONS) {
            throw new DomainException(ErrorMessages.PRODUCT_HOPE_MAX_LENGTH);
        }
        this.sublocations = sublocations;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) throws DomainException {
        if (color == null) {
            throw new DomainException(ErrorMessages.COLOR_NULL);
        }
        Pattern pattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
        Matcher matcher = pattern.matcher(color);
        if (!matcher.matches()) {
            throw new DomainException(ErrorMessages.COLOR_HEXADECIMAL);
        }
        this.color = color;
    }

}
