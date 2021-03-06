package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.entity.Product;
import com.declercq.pieter.datumcontrole.model.exception.db.DatabaseException;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public interface ProductRepository {

    public int size() throws DatabaseException;
    
    public void addProduct(Product product) throws DatabaseException;
    
    public Product getProductByEan(long ean) throws DatabaseException;
    
    public Product getProductByHope(int hope) throws DatabaseException;
    
    public Collection<Product> getAllProducts() throws DatabaseException;
    
    public void updateProduct(Product product) throws DatabaseException;
    
    public void deleteProduct(long ean) throws DatabaseException;
    
}
