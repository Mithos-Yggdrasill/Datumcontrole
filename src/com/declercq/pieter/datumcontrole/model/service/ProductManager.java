package com.declercq.pieter.datumcontrole.model.service;

import com.declercq.pieter.datumcontrole.db.IProductDatabase;
import com.declercq.pieter.datumcontrole.db.SQLiteProductDatabase;
import com.declercq.pieter.datumcontrole.model.entity.Product;
import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.ServiceException;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class ProductManager implements Service {
    
    private IProductDatabase db;
    
    public ProductManager() throws ServiceException {
        try {
            db = new SQLiteProductDatabase();
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }
    
    public void addProduct(Product product) throws ServiceException {
        try {
            db.addProduct(product);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }
        
}
