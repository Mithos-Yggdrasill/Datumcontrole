package com.declercq.pieter.datumcontrole.model.entity;

import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;
import java.util.Objects;

/**
 * This class represents an article.
 *
 * @author Pieter Declercq
 * @version 3.0
 *
 */
public class Product {

    public static final int EAN_MIN_LENGTH = 8;

    public static final int EAN_MAX_LENGTH = 13;

    public static final int HOPE_MIN_LENGTH = 4;

    public static final int HOPE_MAX_LENGTH = 8;

    public static final int NAME_MIN_LENGTH = 2;

    public static final int NAME_MAX_LENGTH = 30;

    /**
     * The EAN of the product.
     */
    private long ean;

    /**
     * The hope of the product.
     */
    private int hope;

    /**
     * The name of the product.
     */
    private String name;

    public Product() {
    }

    public long getEan() {
        return ean;
    }

    public void setEan(long ean) {
        if (ean < 0) {
            throw new IllegalArgumentException(ErrorMessages.PRODUCT_EAN_NEGATIVE);
        }
        if (String.valueOf(ean).length() < EAN_MIN_LENGTH) {
            throw new IllegalArgumentException(ErrorMessages.PRODUCT_EAN_MIN_LENGTH);
        }
        if (String.valueOf(ean).length() > EAN_MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessages.PRODUCT_EAN_MAX_LENGTH);
        }
        this.ean = ean;
    }

    public int getHope() {
        return hope;
    }

    public void setHope(int hope) {
        if (hope < 0) {
            throw new IllegalArgumentException(ErrorMessages.PRODUCT_HOPE_NEGATIVE);
        }
        if (String.valueOf(hope).length() < HOPE_MIN_LENGTH) {
            throw new IllegalArgumentException(ErrorMessages.PRODUCT_HOPE_MIN_LENGTH);
        }
        if (String.valueOf(hope).length() > HOPE_MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessages.PRODUCT_HOPE_MAX_LENGTH);
        }
        this.hope = hope;
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
        hash = 83 * hash + Objects.hashCode(this.ean);
        hash = 83 * hash + this.hope;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        final Product other = (Product) obj;
        return Objects.equals(this.ean, other.ean)
                && this.hope == other.hope
                && Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return ean + "\t" + hope + "\t" + name;
    }

}
