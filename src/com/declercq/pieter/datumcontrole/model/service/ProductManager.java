package com.declercq.pieter.datumcontrole.model.service;

import com.declercq.pieter.datumcontrole.db.ProductRepository;
import com.declercq.pieter.datumcontrole.model.entity.Product;
import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.ServiceException;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class ProductManager {

    private ProductRepository db;

    public ProductManager(ProductRepository db) {
        this.db = db;
    }

    public int getNumberOfProducts() throws ServiceException {
        try {
            return db.size();
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
}
