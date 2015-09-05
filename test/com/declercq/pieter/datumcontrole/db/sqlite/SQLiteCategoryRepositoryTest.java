package com.declercq.pieter.datumcontrole.db.sqlite;

import com.declercq.pieter.datumcontrole.model.entity.Category;
import com.declercq.pieter.datumcontrole.model.exception.db.CategoryAlreadyExistsException;
import com.declercq.pieter.datumcontrole.model.exception.db.CategoryNotFoundException;
import com.declercq.pieter.datumcontrole.model.exception.db.DatabaseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class SQLiteCategoryRepositoryTest {

    private SQLiteCategoryRepository db;
    private int size;
    private List<Category> categoriesToDeleteAfterTest;
    private Category voeding;

    public SQLiteCategoryRepositoryTest() {
    }

    @Before
    public void setUp() throws DatabaseException {
        db = new SQLiteCategoryRepository("jdbc:sqlite:‪DatumControle.sqlite");
        size = db.size();
        categoriesToDeleteAfterTest = new ArrayList<>();
        voeding = new Category("voeding", 11, "#ffa135");
    }

    @After
    public void tearDown() throws DatabaseException {
        for (Category c : categoriesToDeleteAfterTest) {
            db.deleteCategory(c.getName());
        }
        db = null;
        size = 0;
        categoriesToDeleteAfterTest = null;
        voeding = null;
    }

    @Test
    public void addCategory_Adds_category_to_database() throws DatabaseException {
        db.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        assertEquals(size + 1, db.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCategory_IllegalArgumentException_When_Category_is_null() throws DatabaseException {
        voeding = null;
        db.addCategory(voeding);
    }

    @Test(expected = CategoryAlreadyExistsException.class)
    public void addProduct_CategoryAlreadyExistsException_When_already_Category_with_that_name() throws DatabaseException {
        db.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        db.addCategory(voeding);
    }

    @Test
    public void getCategory_Returns_Category_with_that_name() throws DatabaseException {
        db.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        assertEquals(voeding, db.getCategory(voeding.getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCategory_IllegalArgumentException_When_name_is_null() throws DatabaseException {
        String name = null;
        db.getCategory(name);
    }

    @Test(expected = CategoryNotFoundException.class)
    public void getCategory_CategoryNotFoundException_When_no_category_with_that_name() throws DatabaseException {
        db.getCategory(voeding.getName());
    }

    @Test
    public void getAllCategories_Returns_all_categories() throws DatabaseException {
        assertEquals(size, db.getAllCategories().size());
    }

    @Test
    public void updateCategory_Update_info_of_the_category() throws DatabaseException {
        db.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        int sublocations = 15;
        String color = "#000";
        voeding.setSublocations(sublocations);
        voeding.setColor(color);
        db.updateCategory(voeding);
        assertEquals(sublocations, db.getCategory(voeding.getName()).getSublocations());
        assertEquals(color, db.getCategory(voeding.getName()).getColor());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateProduct_IllegalArgumentException_If_product_is_null() throws DatabaseException {
        db.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        int sublocations = 15;
        String color = "#000";
        voeding.setSublocations(sublocations);
        voeding.setColor(color);
        db.updateCategory(null);
    }

    @Test
    public void deleteCategory_Removes_Category_from_database() throws DatabaseException {
        db.addCategory(voeding);
        size = db.size();
        db.deleteCategory(voeding.getName());
        assertEquals(size - 1, db.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCategory_IllegalArgumentException_When_name_is_null() throws DatabaseException {
        String name = null;
        db.deleteCategory(name);
    }

}
