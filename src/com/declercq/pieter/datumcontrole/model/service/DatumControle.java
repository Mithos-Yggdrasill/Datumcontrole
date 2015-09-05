package com.declercq.pieter.datumcontrole.model.service;

import com.declercq.pieter.datumcontrole.db.Repository;
import com.declercq.pieter.datumcontrole.db.RepositoryFactory;
import com.declercq.pieter.datumcontrole.model.entity.Category;
import com.declercq.pieter.datumcontrole.model.entity.Location;
import com.declercq.pieter.datumcontrole.model.entity.Product;
import com.declercq.pieter.datumcontrole.model.exception.db.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.ServiceException;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class DatumControle {

    private Repository db;

    public DatumControle(String dbType) throws ServiceException {
        try {
            db = RepositoryFactory.createDatabase(dbType);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public void addProduct(Product product) throws ServiceException {
        try {
            db.addProduct(product);
        } catch (DatabaseException ex) {
            System.out.println(product);
            throw new ServiceException(ex);
        }
    }

    public Product getProductByEan(Long ean) throws ServiceException {
        try {
            return db.getProductByEan(ean);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public Product getProductByHope(int hope) throws ServiceException {
        try {
            return db.getProductByHope(hope);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public Collection<Product> getAllProducts() throws ServiceException {
        try {
            return db.getAllProducts();
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public void updateProduct(Product product) throws ServiceException {
        try {
            db.updateProduct(product);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public void deleteProduct(Long ean) throws ServiceException {
        try {
            db.deleteProduct(ean);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public void addCategory(Category category) throws ServiceException {
        try {
            db.addCategory(category);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public Category getCategory(String name) throws ServiceException {
        try {
            return db.getCategory(name);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public Collection<Category> getAllCategories() throws ServiceException {
        try {
            return db.getAllCategories();
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public void updateCategory(Category category) throws ServiceException {
        try {
            db.updateCategory(category);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public void deleteCategory(String name) throws ServiceException {
        try {
            db.deleteCategory(name);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public void addLocation(Location location) throws ServiceException {
        try {
            db.addLocation(location);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public Location getLocation(String name) throws ServiceException {
        try {
            return db.getLocation(name);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public Collection<Location> getAllLocations() throws ServiceException {
        try {
            return db.getAllLocations();
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public void updateLocation(Location location) throws ServiceException {
        try {
            db.updateLocation(location);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

    public void deleteLocation(String name) throws ServiceException {
        try {
            db.deleteLocation(name);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }

}