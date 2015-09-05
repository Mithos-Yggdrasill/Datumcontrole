package com.declercq.pieter.datumcontrole.model.service;

import com.declercq.pieter.datumcontrole.db.CategoryRepository;
import com.declercq.pieter.datumcontrole.model.entity.Category;
import com.declercq.pieter.datumcontrole.model.exception.db.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.ServiceException;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class CategoryManager {

    private CategoryRepository db;
    
    public CategoryManager(CategoryRepository db){
        this.db = db;
    }
    
    public int getNumberOfCategories() throws ServiceException {
        try {
            return db.size();
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

    
}