/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.entity.Product;
import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class SQLiteProductDatabaseTest {

    private SQLiteProductDatabase db;
    private int size;
    private List<Product> productsToDeleteAfterTest;
    private Product roomijs;

    public SQLiteProductDatabaseTest() {
    }

    @Before
    public void setUp() throws Exception {
        db = new SQLiteProductDatabase();
        size = db.size();
        productsToDeleteAfterTest = new ArrayList<>();
        roomijs = new Product(5412121000114L, 16308, "2.5L ROOMIJS VANILLE");
    }

    @After
    public void tearDown() throws Exception {
        for (Product p : productsToDeleteAfterTest) {
            db.deleteProduct(p.getEan());
        }
        db = null;
        size = 0;
        productsToDeleteAfterTest = null;
        roomijs = null;
    }

    @Test
    public void addProduct_Adds_product_to_database() throws DatabaseException {
        db.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        assertEquals(size + 1, db.size());
    }

    @Test(expected = DatabaseException.class)
    public void addProduct_DatabaseException_When_Product_is_null() throws DatabaseException {
        roomijs = null;
        db.addProduct(roomijs);
    }

    @Test(expected = DatabaseException.class)
    public void addProduct_DatabaseException_When_already_Product_with_that_ean() throws DatabaseException {
        db.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        db.addProduct(roomijs);
    }

    @Test
    public void getProductByEan_Returns_Product_with_that_ean() throws DatabaseException {
        db.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        assertEquals(roomijs, db.getProductByEan(roomijs.getEan()));
    }

    @Test(expected = DatabaseException.class)
    public void getProductByEan_DatabaseException_When_ean_is_null() throws DatabaseException {
        Long ean = null;
        db.getProductByEan(ean);
    }

    @Test(expected = DatabaseException.class)
    public void getProductByEan_DatabaseException_When_no_product_with_that_ean() throws DatabaseException {
        db.getProductByEan(roomijs.getEan());
    }
    
    @Test
    public void getAllProducts_Returns_all_products() throws DatabaseException {
        assertEquals(size, db.getAllProducts().size());
    }

    @Test
    public void deleteProduct_Removes_product_from_database() throws DatabaseException {
        db.addProduct(roomijs);
        size = db.size();
        db.deleteProduct(roomijs.getEan());
        assertEquals(size - 1, db.size());
    }

    @Test(expected = DatabaseException.class)
    public void deleteProduct_DatabaseException_When_ean_is_null() throws DatabaseException {
        Long ean = null;
        db.deleteProduct(ean);
    }

}
