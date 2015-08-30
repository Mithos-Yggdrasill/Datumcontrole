package com.declercq.pieter.datumcontrole.model.exception;

import com.declercq.pieter.datumcontrole.model.entity.Product;

public interface ErrorMessages {

    /**
     * The error-message-code referring to a null entered as EAN.
     */
    public final static String PRODUCT_EAN_NULL = "Er werd null meegegeven als barcode";

    /**
     * The error-message-code referring to a non valid length of the barcode.
     */
    public final static String PRODUCT_EAN_MIN_LENGTH = "De barcode moet minstens " + Product.MIN_AMOUNT_OF_CIPHERS_EAN + " cijfers bevatten";

    /**
     * The error-message-code referring to a non valid length of the barcode.
     */
    public final static String PRODUCT_EAN_MAX_LENGTH = "De barcode mag maximaal " + Product.MAX_AMOUNT_OF_CIPHERS_EAN + " cijfers bevatten";

    /**
     * The error-message-code referring to a non positive value of the barcode.
     */
    public final static String PRODUCT_EAN_NEGATIVE = "De barcode moet positief zijn";

    /**
     * The error-message-code referring to a hope number with less digits than allowed.
     */
    public final static String PRODUCT_HOPE_MIN_LENGTH = "De hope moet minstens " + Product.MIN_AMOUNT_OF_CIPHERS_HOPE + " cijfers bevatten";

    /**
     * The error-message-code referring to a non valid hope number.
     */
    public final static String PRODUCT_HOPE_MAX_LENGTH = "De hope mag maximaal " + Product.MAX_AMOUNT_OF_CIPHERS_HOPE + " cijfers bevatten";

    /**
     * The error-message-code referring to a non valid hope number.
     */
    public final static String PRODUCT_HOPE_NEGATIVE = "De hope moet positief zijn";
    
    /**
     * The error-message-code referring to an error occurred when the given
     * category is null.
     */
    public static final String CATEGORY_NULL = "err_cat_null";

    /**
     * The error-message-code referring to a non valid length of the category
     * name.
     */
    public final static String CATEGORY_NAME = "err_cat_name";

    /**
     * The error-message-code referring to a non valid spot.
     */
    public final static String CATEGORY_SPOT = "err_cat_spot";

    /**
     * The error-message-code referring to a non valid amount of subdomains.
     */
    public final static String CATEGORY_SUBDOMAINS = "err_cat_sub";

    /**
     * The error-message-code referring to an error occurred when the searched
     * category is not in this database.
     */
    public final static String CATEGORY_NOT_FOUND = "err_catdb_cat_not_found";

    /**
     * The error-message-code referring to an error occurred when the database
     * is already in the database.
     */
    public final static String CATEGORY_ALREADY_EXISTS = "err_catdb_cat_already_exists";

    /**
     * The error-message-code referring to a date in the past.
     */
    public final static String DATE_PAST = "err_date_past";

    /**
     * The error-message-code referring to a non valid length of the product
     * name.
     */
    public final static String PRODUCT_NAME = "err_prod_name";

    /**
     * The error-message-code referring to a non valid category.
     */
    public final static String PRODUCT_CATEGORY = "err_prod_cat";

    /**
     * The error-message-code referring to a null product.
     */
    public final static String PRODUCT_NULL = "err_prod_null";

    /**
     * The error-message-code referring to an error occurred when the product
     * already exists in this database.
     */
    public final static String PRODUCT_ALREADY_EXISTS = "err_catproddb_prod_already_exist";

    /**
     * The error-message-code referring to an error occurred when the searched
     * product doesn't exists in this database.
     */
    public final static String PRODUCT_NOT_FOUND = "err_catproddb_prod_not_found";

    /**
     * The error-message-code referring to a non valid date of the
     * expiryProduct.
     */
    public final static String EXPIRYPRODUCT_EXPIRYDATE = "err_exp_date";

    /**
     * The error-message-code referring to a non valid date of the
     * expiryProduct.
     */
    public final static String EXPIRYPRODUCT_SPOT = "err_exp_spot";

    /**
     * The error-message-code referring to an error occurred when the given
     * expiryProduct is null.
     */
    public static final String EXPIRYPRODUCT_NULL = "err_exp_null";

    /**
     * The error-message-code referring to an error occurred when the searched
     * expiryProduct is not in this database.
     */
    public static final String EXPIRYPRODUCT_NOT_FOUND = "err_exp_not_found";

    /**
     * The error-message-code referring to an error occurred when the
     * expiryProduct you want to add already exists in this database.
     */
    public static final String EXPIRYPRODUCT_ALREADY_EXISTS = "err_exp_already_exists";

    /**
     * The error-message-code referring to an error occurred when the expiryList
     * you want to add already exists in this database.
     */
    public static final String EXPIRYLIST_ALREADY_EXISTS = "err_exl_already_exists";

    /**
     * The error-message-code referring to an error occurred when the searched
     * expiryList doesn't exists in this database.
     */
    public static final String EXPIRYLIST_NOT_FOUND = "err_exl_not_found";

    /**
     * The error-meesage-code referring to an error occurred when tried to
     * update an update with itself.
     */
    public static final String SAME_ARGUMENTS = "err_same_arguments";

    /**
     * The error-message-code referring to an error occurred when the default
     * values are not correct.
     */
    public static final String WRONG_DEFAULTS = "wrong_defaults";

}
