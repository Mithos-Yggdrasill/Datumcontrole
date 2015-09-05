package com.declercq.pieter.datumcontrole.model.service;

import com.declercq.pieter.datumcontrole.model.exception.ServiceException;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class Service {

    public static void main(String[] args) throws ServiceException {
        DatumControle service = new DatumControle("sqlite");
        System.out.println(service.getAllCategories());
    }

}
