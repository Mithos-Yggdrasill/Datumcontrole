package com.declercq.pieter.datumcontrole.model.service;

import com.declercq.pieter.datumcontrole.db.IProductDatabase;
import com.declercq.pieter.datumcontrole.db.SQLiteProductDatabase;
import com.declercq.pieter.datumcontrole.model.entity.Product;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class ProductManager implements Service {
    
    private IProductDatabase db;
    
    public ProductManager() {
        db = new SQLiteProductDatabase();
    }
    
    public void addProduct(Product product) {
        db.addProduct(product);
    }
        
}
