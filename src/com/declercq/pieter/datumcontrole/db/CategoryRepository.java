package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.entity.Category;
import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public interface CategoryRepository {
    
    public int size() throws DatabaseException;
    
    public void addCategory(Category category) throws DatabaseException;
    
    public Category getCategory(String name) throws DatabaseException;
        
    public Collection<Category> getAllCategories() throws DatabaseException;
    
    public void updateCategory(Category category) throws DatabaseException;
    
    public void deleteCategory(String name) throws DatabaseException;

}
