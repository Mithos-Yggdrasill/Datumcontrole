package com.declercq.pieter.datumcontrole.model.entity;

import com.declercq.pieter.datumcontrole.model.exception.DomainException;
import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;

/**
 * This class represents an article.
 *
 * @author Pieter Declercq
 * @version 3.0
 *
 */
public class Product {

    public static final int MIN_AMOUNT_OF_CIPHERS_EAN = 8;
    
    public static final int MAX_AMOUNT_OF_CIPHERS_EAN = 13;
    
    public static final int MIN_AMOUNT_OF_CIPHERS_HOPE = 4;
    
    public static final int MAX_AMOUNT_OF_CIPHERS_HOPE = 8;
    
    private Long ean;
    
    private int hope;
    
    private String name;
    
    public Product(){
        
    }
    
    public Product(Long ean, int hope, String name) throws DomainException{
        setEan(ean);
        setHope(hope);
        setName(name);
    }
    

    public Long getEan() {
        return ean;
    }

    public void setEan(Long ean) throws DomainException {
        if(ean == null){
            throw new DomainException(ErrorMessages.PRODUCT_EAN_NULL);
        }
        if(ean < 0){
            throw new DomainException(ErrorMessages.PRODUCT_EAN_NEGATIVE);
        }
        if(String.valueOf(ean).length() < MIN_AMOUNT_OF_CIPHERS_EAN){
            throw new DomainException(ErrorMessages.PRODUCT_EAN_MIN_LENGTH);
        }
        if(String.valueOf(ean).length() > MAX_AMOUNT_OF_CIPHERS_EAN){
            throw new DomainException(ErrorMessages.PRODUCT_EAN_MAX_LENGTH);
        }
        this.ean = ean;
    }

    public int getHope() {
        return hope;
    }

    public void setHope(int hope) throws DomainException {
        if(hope < 0){
            throw new DomainException(ErrorMessages.PRODUCT_HOPE_NEGATIVE);
        }
        if(String.valueOf(hope).length() < MIN_AMOUNT_OF_CIPHERS_HOPE){
            throw new DomainException(ErrorMessages.PRODUCT_HOPE_MIN_LENGTH);
        }
        if(String.valueOf(hope).length() > MAX_AMOUNT_OF_CIPHERS_HOPE){
            throw new DomainException(ErrorMessages.PRODUCT_HOPE_MAX_LENGTH);
        }
        this.hope = hope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
