package com.declercq.pieter.datumcontrole.model.entity;

import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;
import java.util.Objects;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class Location {

    public static final int NAME_MIN_LENGTH = 2;

    public static final int NAME_MAX_LENGTH = 20;

    private String name;

    public Location() {

    }

    public Location(String name) {
        setName(name);
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
            throw new IllegalArgumentException(ErrorMessages.PRODUCT_NAME_MIN_LENGTH);
        }
        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessages.PRODUCT_NAME_MAX_LENGTH);
        }
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Location)) {
            return false;
        }
        final Location other = (Location) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
