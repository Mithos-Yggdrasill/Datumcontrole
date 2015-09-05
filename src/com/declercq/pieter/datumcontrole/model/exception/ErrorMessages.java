package com.declercq.pieter.datumcontrole.model.exception;

import com.declercq.pieter.datumcontrole.model.entity.Category;
import com.declercq.pieter.datumcontrole.model.entity.Product;

public interface ErrorMessages {

    /**
     * The error-message-code referring to a null entered as EAN.
     */
    public final static String EAN_NULL = "Er werd null meegegeven als barcode";

    /**
     * The error-message-code referring to a non valid length of the barcode.
     */
    public final static String PRODUCT_EAN_MIN_LENGTH = "De barcode moet minstens " + Product.MIN_AMOUNT_OF_DIGITS_EAN + " cijfers bevatten";

    /**
     * The error-message-code referring to a non valid length of the barcode.
     */
    public final static String PRODUCT_EAN_MAX_LENGTH = "De barcode mag maximaal " + Product.MAX_AMOUNT_OF_DIGITS_EAN + " cijfers bevatten";

    /**
     * The error-message-code referring to a non positive value of the barcode.
     */
    public final static String PRODUCT_EAN_NEGATIVE = "De barcode moet positief zijn";

    /**
     * The error-message-code referring to a hope number with less digits than
     * allowed.
     */
    public final static String PRODUCT_HOPE_MIN_LENGTH = "De hope moet minstens " + Product.MIN_AMOUNT_OF_DIGITS_HOPE + " cijfers bevatten";

    /**
     * The error-message-code referring to a non valid hope number.
     */
    public final static String PRODUCT_HOPE_MAX_LENGTH = "De hope mag maximaal " + Product.MAX_AMOUNT_OF_DIGITS_HOPE + " cijfers bevatten";

    /**
     * The error-message-code referring to a non valid hope number.
     */
    public final static String PRODUCT_HOPE_NEGATIVE = "De hope moet positief zijn";

    /**
     * The error-message-code referring to a name consisting of non-valid
     * characters.
     */
    public final static String NAME_NOT_ALPHANUMERIC = "De naam mag enkel uit een combinatie van letters and cijfers bestaan.";

    public final static String NAME_NULL = "Er werd null meegegeven als naam";

    /**
     * The error-message-code referring to a name with less characters than
     * allowed.
     */
    public final static String PRODUCT_NAME_MIN_LENGTH = "De naam moet minstens " + Product.MIN_AMOUNT_OF_CHARACTERS_NAME + " letters bevatten";

    /**
     * The error-message-code referring to a name with less characters than
     * allowed.
     */
    public final static String PRODUCT_NAME_MAX_LENGTH = "De naam mag maximaal uit " + Product.MAX_AMOUNT_OF_CHARACTERS_NAME + " letters bestaan";

    /**
     * The error-message-code referring to a name with less characters than
     * allowed.
     */
    public final static String DATABASE_NOT_FOUND = "De connectie met de database is mislukt";

    /**
     * The error-message-code referring to a name with less characters than
     * allowed.
     */
    public final static String DATABASE_DRIVER_NOT_LOADED = "Geen driver voor deze database geladen";

    public final static String DATABASE_FAULT_IN_QUERY = "De uitgevoerde sql-query bevat een fout";

    public final static String DATABASE_CLOSSING_CONNECTION = "Een fout trad op bij het sluiten van de connectie";

    public final static String PRODUCT_ALREADY_EXISTS = "Er bestaat reeds een product met die barcode";

    /**
     * The error-message-code referring to a null product.
     */
    public final static String PRODUCT_NULL = "Er werd null meegegeven als product";

    /**
     * The error-message-code referring to an error occurred when the searched
     * product doesn't exists in this database.
     */
    public final static String PRODUCT_NOT_FOUND_EAN = "Er bestaat geen product met die barcode";

    public final static String PRODUCT_NOT_FOUND_HOPE = "Er bestaat geen product met die hope";

    /**
     * The error-message-code referring to a name with less characters than
     * allowed.
     */
    public final static String CATEGORY_NAME_MIN_LENGTH = "De naam moet minstens " + Category.MIN_AMOUNT_OF_CHARACTERS_NAME + " letters bevatten";

    /**
     * The error-message-code referring to a name with less characters than
     * allowed.
     */
    public final static String CATEGORY_NAME_MAX_LENGTH = "De naam mag maximaal uit " + Category.MAX_AMOUNT_OF_CHARACTERS_NAME + " letters bestaan";

    public final static String COLOR_NOT_HEXADECIMAL = "De kleur moet in hexadecimaal formaat gegeven zijn.";

    public final static String COLOR_NULL = "Er werd null meegegeven als kleur";

    public final static String ID_NULL = "Er werd null meegegeven als id";

    /**
     * The error-message-code referring to an error occurred when the given
     * category is null.
     */
    public static final String CATEGORY_NULL = "Er werd null meegegeven als category";

    /**
     * The error-message-code referring to an error occurred when the searched
     * category is not in this database.
     */
    public final static String CATEGORY_NOT_FOUND = "Er is geen categorie met die naam";

    /**
     * The error-message-code referring to an error occurred when the database
     * is already in the database.
     */
    public final static String CATEGORY_ALREADY_EXISTS = "Er bestaat reeds een categorie met die naam";

    public final static String LOCATION_NULL = "Er werd null meegegeven als locatie";
    
    public final static String LOCATION_ALREADY_EXISTS = "Er bestaat reeds een locatie met die naam";
    
    public final static String LOCATION_NOT_FOUND = "Er bestaat geen locatie met die naam";
    
    public final static String DATABASETYPE_NOT_SUPPORTED = "Dit type van databank wordt niet ondersteund";
    
}
