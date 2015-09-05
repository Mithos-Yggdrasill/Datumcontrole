package com.declercq.pieter.datumcontrole.db.sqlite;

import com.declercq.pieter.datumcontrole.db.Repository;
import com.declercq.pieter.datumcontrole.db.SQLiteLocationDatabase;
import com.declercq.pieter.datumcontrole.db.SQLiteProductDatabase;
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
public class SQLiteRepository implements Repository {
    
    private SQLiteProductDatabase productDb;
    
    private SQLiteCategoryRepository categoryDb;
    
    private SQLiteLocationDatabase locationDb;
    
    public SQLiteRepository(String url) throws DatabaseException {
        productDb = new SQLiteProductDatabase(url);
        categoryDb = new SQLiteCategoryRepository(url);
        locationDb = new SQLiteLocationDatabase(url);
    }
    
    @Override
    public void addProduct(Product product) throws DatabaseException {
        productDb.addProduct(product);
    }
    
    @Override
    public Product getProductByEan(Long ean) throws DatabaseException {
        return productDb.getProductByEan(ean);
    }
    
    @Override
    public Product getProductByHope(int hope) throws DatabaseException {
        return productDb.getProductByHope(hope);
    }
    
    @Override
    public Collection<Product> getAllProducts() throws DatabaseException {
        return productDb.getAllProducts();
    }
    
    @Override
    public void updateProduct(Product product) throws DatabaseException {
        productDb.updateProduct(product);
    }
    
    @Override
    public void deleteProduct(Long ean) throws DatabaseException {
        productDb.deleteProduct(ean);
    }
    
    @Override
    public void addCategory(Category category) throws DatabaseException {
        categoryDb.addCategory(category);
    }
    
    @Override
    public Category getCategory(String name) throws DatabaseException {
        return categoryDb.getCategory(name);
    }
    
    @Override
    public Collection<Category> getAllCategories() throws DatabaseException {
        return categoryDb.getAllCategories();
    }
    
    @Override
    public void updateCategory(Category category) throws DatabaseException {
        categoryDb.updateCategory(category);
    }
    
    @Override
    public void deleteCategory(String name) throws DatabaseException {
        categoryDb.deleteCategory(name);
    }
    
    @Override
    public void addLocation(Location location) throws DatabaseException {
        locationDb.addLocation(location);
    }
    
    @Override
    public Location getLocation(String name) throws DatabaseException {
        return locationDb.getLocation(name);
    }
    
    @Override
    public Collection<Location> getAllLocations() throws DatabaseException {
        return locationDb.getAllLocations();
    }
    
    @Override
    public void updateLocation(Location location) throws DatabaseException {
        locationDb.updateLocation(location);
    }
    
    @Override
    public void deleteLocation(String name) throws DatabaseException {
        locationDb.deleteLocation(name);
    }
    
}
