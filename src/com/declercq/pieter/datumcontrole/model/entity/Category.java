package com.declercq.pieter.datumcontrole.model.entity;

import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class Category {

    public static final int NAME_MIN_LENGTH = 2;

    public static final int NAME_MAX_LENGTH = 20;

    public static final int SUBLOCATIONS_MIN_AMOUNT = 1;

    public static final int SUBLOCATIONS_MAX_AMOUNT = 20;

    private String name;

    private int sublocations;

    private String color;

    public Category() {
    }

    public Category(String name, int sublocations, String color) {
        setName(name);
        setSublocations(sublocations);
        setColor(color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ErrorMessages.NAME_NULL);
        }
        name = name.replaceAll(System.getProperty("line.separator"), "");
        name = name.replaceAll("\r|\n", "");
        name = name.toLowerCase().trim();
        if (!name.matches("^[.,a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException(ErrorMessages.NAME_NOT_ALPHANUMERIC);
        }
        if (name.length() < NAME_MIN_LENGTH) {
            throw new IllegalArgumentException(ErrorMessages.CATEGORY_NAME_MIN_LENGTH);
        }
        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessages.CATEGORY_NAME_MAX_LENGTH);
        }
        this.name = name;
    }

    public int getSublocations() {
        return sublocations;
    }

    public void setSublocations(int sublocations) {
        if (sublocations < SUBLOCATIONS_MIN_AMOUNT) {
            throw new IllegalArgumentException(ErrorMessages.PRODUCT_HOPE_MIN_LENGTH);
        }
        if (sublocations > SUBLOCATIONS_MAX_AMOUNT) {
            throw new IllegalArgumentException(ErrorMessages.PRODUCT_HOPE_MAX_LENGTH);
        }
        this.sublocations = sublocations;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null) {
            throw new IllegalArgumentException(ErrorMessages.COLOR_NULL);
        }
        Pattern pattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
        Matcher matcher = pattern.matcher(color);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorMessages.COLOR_NOT_HEXADECIMAL);
        }
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + this.sublocations;
        hash = 23 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Category)) {
            return false;
        }
        final Category other = (Category) obj;
        return (Objects.equals(this.name, other.name)) 
                && (this.sublocations == other.sublocations) 
                && (Objects.equals(this.color, other.color));
    }

    @Override
    public String toString() {
        return this.name;
    }

}
