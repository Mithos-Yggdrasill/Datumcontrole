package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.entity.Category;
import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.DomainException;
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
public class SQLiteCategoryDatabaseTest {

    private SQLiteCategoryDatabase db;
    private int size;
    private List<Category> categoriesToDeleteAfterTest;
    private Category voeding;

    public SQLiteCategoryDatabaseTest() {
    }

    @Before
    public void setUp() throws DatabaseException, DomainException {
        db = new SQLiteCategoryDatabase("jdbc:sqlite:‪DatumControle.sqlite");
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

    @Test(expected = DatabaseException.class)
    public void addCategory_DatabaseException_When_Category_is_null() throws DatabaseException {
        voeding = null;
        db.addCategory(voeding);
    }

    @Test(expected = DatabaseException.class)
    public void addProduct_DatabaseException_When_already_Category_with_that_name() throws DatabaseException {
        db.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        db.addCategory(voeding);
    }

    @Test
    public void deleteCategory_Removes_Category_from_database() throws DatabaseException {
        db.addCategory(voeding);
        size = db.size();
        db.deleteCategory(voeding.getName());
        assertEquals(size - 1, db.size());
    }

    @Test(expected = DatabaseException.class)
    public void deleteCategory_DatabaseException_When_name_is_null() throws DatabaseException {
        String name = null;
        db.deleteCategory(name);
    }

}
