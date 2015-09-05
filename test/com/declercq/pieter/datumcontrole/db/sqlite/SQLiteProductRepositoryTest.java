package com.declercq.pieter.datumcontrole.db.sqlite;

import com.declercq.pieter.datumcontrole.model.entity.Product;
import com.declercq.pieter.datumcontrole.model.exception.db.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.db.ProductAlreadyExistsException;
import com.declercq.pieter.datumcontrole.model.exception.db.ProductNotFoundException;
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
public class SQLiteProductRepositoryTest {
    
    private SQLiteProductRepository db;
    private int size;
    private List<Product> productsToDeleteAfterTest;
    private Product roomijs;
    
    public SQLiteProductRepositoryTest() {
    }
    
    @Before
    public void setUp() throws Exception {
        db = new SQLiteProductRepository("jdbc:sqlite:‪DatumControle.sqlite");
        size = db.size();
        productsToDeleteAfterTest = new ArrayList<>();
        roomijs = new Product();
        roomijs.setEan(5412121000114L);
        roomijs.setHope(16308);
        roomijs.setName("2.5L ROOMIJS VANILLE");
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
    
    @Test(expected = IllegalArgumentException.class)
    public void addProduct_IllegalArgumentException_When_Product_is_null() throws DatabaseException {
        roomijs = null;
        db.addProduct(roomijs);
    }
    
    @Test(expected = ProductAlreadyExistsException.class)
    public void addProduct_ProductAlreadyExistsException_When_already_Product_with_that_ean() throws DatabaseException {
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
    
    @Test(expected = ProductNotFoundException.class)
    public void getProductByEan_ProductNotFoundException_When_no_product_with_that_ean() throws DatabaseException {
        db.getProductByEan(roomijs.getEan());
    }
    
    @Test
    public void getProductByHope_Returns_Product_with_that_hope() throws DatabaseException {
        db.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        assertEquals(roomijs, db.getProductByHope(roomijs.getHope()));
    }
    
    @Test(expected = DatabaseException.class)
    public void getProductByHope_DatabaseException_When_no_product_with_that_hope() throws DatabaseException {
        db.getProductByHope(roomijs.getHope());
    }
    
    @Test
    public void getAllProducts_Returns_all_products() throws DatabaseException {
        assertEquals(size, db.getAllProducts().size());
    }
    
    @Test
    public void updateProduct_Update_info_of_the_product() throws DatabaseException {
        db.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        int hope = 9876;
        String name = "nieuwe naam";
        roomijs.setHope(hope);
        roomijs.setName(name);
        db.updateProduct(roomijs);
        assertEquals(hope, db.getProductByEan(roomijs.getEan()).getHope());
        assertEquals(name, db.getProductByEan(roomijs.getEan()).getName());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateProduct_IllegalArgumentException_If_product_is_null() throws DatabaseException {
        db.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        int hope = 9876;
        String name = "nieuwe naam";
        roomijs.setHope(hope);
        roomijs.setName(name);
        db.updateProduct(null);
    }
    
    @Test
    public void deleteProduct_Removes_product_from_database() throws DatabaseException {
        db.addProduct(roomijs);
        size = db.size();
        db.deleteProduct(roomijs.getEan());
        assertEquals(size - 1, db.size());
    }
    
}
