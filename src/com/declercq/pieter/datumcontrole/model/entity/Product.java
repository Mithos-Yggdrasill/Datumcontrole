package com.declercq.pieter.datumcontrole.model.entity;

import com.declercq.pieter.datumcontrole.model.exception.DomainException;
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

    /**
     * The minimum amount of digits the ean of a product must have.
     */
    public static final int MIN_AMOUNT_OF_CIPHERS_EAN = 8;

    /**
     * The maximum amount of digits the ean of a product must have.
     */
    public static final int MAX_AMOUNT_OF_CIPHERS_EAN = 13;

    /**
     * The minimum amount of digits the hope of a product must have.
     */
    public static final int MIN_AMOUNT_OF_CIPHERS_HOPE = 4;

    /**
     * The maximum amount of digits the hope of a product must have.
     */
    public static final int MAX_AMOUNT_OF_CIPHERS_HOPE = 8;

    /**
     * The minimum amount of characters the name of a product must have.
     */
    public static final int MIN_AMOUNT_OF_CHARACTERS_NAME = 2;

    /**
     * The maximum amount of characters the name of a product may have.
     */
    public static final int MAX_AMOUNT_OF_CHARACTERS_NAME = 30;

    /**
     * The EAN of the product.
     */
    private Long ean;

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

    public Product(Long ean, int hope, String name) throws DomainException {
        setEan(ean);
        setHope(hope);
        setName(name);
    }

    public Long getEan() {
        return ean;
    }

    public void setEan(Long ean) throws DomainException {
        if (ean == null) {
            throw new DomainException(ErrorMessages.PRODUCT_EAN_NULL);
        }
        if (ean < 0) {
            throw new DomainException(ErrorMessages.PRODUCT_EAN_NEGATIVE);
        }
        if (String.valueOf(ean).length() < MIN_AMOUNT_OF_CIPHERS_EAN) {
            throw new DomainException(ErrorMessages.PRODUCT_EAN_MIN_LENGTH);
        }
        if (String.valueOf(ean).length() > MAX_AMOUNT_OF_CIPHERS_EAN) {
            throw new DomainException(ErrorMessages.PRODUCT_EAN_MAX_LENGTH);
        }
        this.ean = ean;
    }

    public int getHope() {
        return hope;
    }

    public void setHope(int hope) throws DomainException {
        if (hope < 0) {
            throw new DomainException(ErrorMessages.PRODUCT_HOPE_NEGATIVE);
        }
        if (String.valueOf(hope).length() < MIN_AMOUNT_OF_CIPHERS_HOPE) {
            throw new DomainException(ErrorMessages.PRODUCT_HOPE_MIN_LENGTH);
        }
        if (String.valueOf(hope).length() > MAX_AMOUNT_OF_CIPHERS_HOPE) {
            throw new DomainException(ErrorMessages.PRODUCT_HOPE_MAX_LENGTH);
        }
        this.hope = hope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws DomainException {
        name = name.replaceAll(System.getProperty("line.separator"), "");
        name = name.replaceAll("\r|\n", "");
        name = name.toLowerCase().trim();
        if (!name.matches("^[.,a-zA-Z0-9 ]+$")) {
            throw new DomainException(ErrorMessages.PRODUCT_NAME_ALPHANUMERIC);
        }
        if (name.length() < MIN_AMOUNT_OF_CHARACTERS_NAME) {
            throw new DomainException(ErrorMessages.PRODUCT_NAME_MIN_LENGTH);
        }
        if (name.length() > MAX_AMOUNT_OF_CHARACTERS_NAME) {
            throw new DomainException(ErrorMessages.PRODUCT_NAME_MAX_LENGTH);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.ean, other.ean)) {
            return false;
        }
        if (this.hope != other.hope) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    
    
}