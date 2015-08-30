package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.entity.Product;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public interface IProductDatabase {

    public void addProduct(Product product);
    
    public Product getProduct(Long ean);
    
    public Product getProduct(int hope);
    
    public Collection<Product> getAllProducts();
    
    public void updateProduct(Product product);
    
    public void deleteProduct(Long ean);
    
}
