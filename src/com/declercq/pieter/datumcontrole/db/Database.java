package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.entity.Category;
import com.declercq.pieter.datumcontrole.model.entity.Location;
import com.declercq.pieter.datumcontrole.model.entity.Product;
import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public interface Database {
    
    public void addProduct(Product product) throws DatabaseException;
    
    public Product getProductByEan(Long ean) throws DatabaseException;
    
    public Product getProductByHope(int hope) throws DatabaseException;
    
    public Collection<Product> getAllProducts() throws DatabaseException;
    
    public void updateProduct(Product product) throws DatabaseException;
    
    public void deleteProduct(Long ean) throws DatabaseException;

    public void addCategory(Category category) throws DatabaseException;
    
    public Category getCategory(String name) throws DatabaseException;
        
    public Collection<Category> getAllCategories() throws DatabaseException;
    
    public void updateCategory(Category category) throws DatabaseException;
    
    public void deleteCategory(String name) throws DatabaseException;

    public void addLocation(Location location) throws DatabaseException;
    
    public Location getLocation(String name) throws DatabaseException;
        
    public Collection<Location> getAllLocations() throws DatabaseException;
    
    public void updateLocation(Location location) throws DatabaseException;
    
    public void deleteLocation(String name) throws DatabaseException;
    
}
