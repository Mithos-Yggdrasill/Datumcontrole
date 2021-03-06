package com.declercq.pieter.datumcontrole.model.service;

import com.declercq.pieter.datumcontrole.db.sqlite.SQLiteCategoryRepository;
import com.declercq.pieter.datumcontrole.model.entity.Category;
import com.declercq.pieter.datumcontrole.model.exception.ServiceException;
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
public class CategoryManagerTest {

    private CategoryManager service;
    private int size;
    private List<Category> categoriesToDeleteAfterTest;
    private Category voeding;

    public CategoryManagerTest() {
    }

    @Before
    public void setUp() throws Exception {
        service = new CategoryManager(new SQLiteCategoryRepository("jdbc:sqlite:‪DatumControle.sqlite"));
        size = service.getNumberOfCategories();
        categoriesToDeleteAfterTest = new ArrayList<>();
        voeding = new Category();
        voeding.setName("voeding");
        voeding.setSublocations(11);
        voeding.setColor("#ffa135");
    }

    @After
    public void tearDown() throws ServiceException {
        for (Category c : categoriesToDeleteAfterTest) {
            service.deleteCategory(c.getName());
        }
        service = null;
        size = 0;
        categoriesToDeleteAfterTest = null;
        voeding = null;
    }

    @Test
    public void addCategory_Adds_category_to_database() throws ServiceException {
        service.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        assertEquals(size + 1, service.getNumberOfCategories());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCategory_IllegalArgumentException_When_Category_is_null() throws ServiceException {
        voeding = null;
        service.addCategory(voeding);
    }

    @Test(expected = ServiceException.class)
    public void addProduct_ServiceException_When_already_Category_with_that_name() throws ServiceException {
        service.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        service.addCategory(voeding);
    }

    @Test
    public void getCategory_Returns_Category_with_that_name() throws ServiceException {
        service.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        assertEquals(voeding, service.getCategory(voeding.getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCategory_IllegalArgumentException_When_name_is_null() throws ServiceException {
        String name = null;
        service.getCategory(name);
    }

    @Test(expected = ServiceException.class)
    public void getCategory_ServiceException_When_no_category_with_that_name() throws ServiceException {
        service.getCategory(voeding.getName());
    }

    @Test
    public void getAllCategories_Returns_all_categories() throws ServiceException {
        assertEquals(size, service.getAllCategories().size());
    }

    @Test
    public void updateCategory_Update_info_of_the_category() throws ServiceException {
        service.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        int sublocations = 15;
        String color = "#000";
        voeding.setSublocations(sublocations);
        voeding.setColor(color);
        service.updateCategory(voeding);
        assertEquals(sublocations, service.getCategory(voeding.getName()).getSublocations());
        assertEquals(color, service.getCategory(voeding.getName()).getColor());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateProduct_IllegalArgumentException_If_product_is_null() throws ServiceException {
        service.addCategory(voeding);
        categoriesToDeleteAfterTest.add(voeding);
        int sublocations = 15;
        String color = "#000";
        voeding.setSublocations(sublocations);
        voeding.setColor(color);
        service.updateCategory(null);
    }

    @Test
    public void deleteCategory_Removes_Category_from_database() throws ServiceException {
        service.addCategory(voeding);
        size = service.getNumberOfCategories();
        service.deleteCategory(voeding.getName());
        assertEquals(size - 1, service.getNumberOfCategories());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCategory_IllegalArgumentException_When_name_is_null() throws ServiceException {
        String name = null;
        service.deleteCategory(name);
    }

}
