package com.declercq.pieter.datumcontrole.model.service;

import com.declercq.pieter.datumcontrole.db.sqlite.SQLiteProductRepository;
import com.declercq.pieter.datumcontrole.model.entity.Product;
import com.declercq.pieter.datumcontrole.model.exception.db.DatabaseException;
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
public class ProductManagerTest {

    private ProductManager service;
    private int size;
    private List<Product> productsToDeleteAfterTest;
    private Product roomijs;

    public ProductManagerTest() {
    }

    @Before
    public void setUp() throws ServiceException, DatabaseException {
        service = new ProductManager(new SQLiteProductRepository("jdbc:sqlite:‪DatumControle.sqlite"));
        size = service.getNumberOfProducts();
        productsToDeleteAfterTest = new ArrayList<>();
        roomijs = new Product(5412121000114L, 16308, "2.5L ROOMIJS VANILLE");

    }

    @After
    public void tearDown() throws ServiceException {
        for (Product p : productsToDeleteAfterTest) {
            service.deleteProduct(p.getEan());
        }
        service = null;
        size = 0;
        productsToDeleteAfterTest = null;
        roomijs = null;

    }

    @Test
    public void addProduct_Adds_product_to_database() throws ServiceException {
        service.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        assertEquals(size + 1, service.getNumberOfProducts());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProduct_IllegalArgumentException_When_Product_is_null() throws ServiceException {
        roomijs = null;
        service.addProduct(roomijs);
    }

    @Test(expected = ServiceException.class)
    public void addProduct_DatabaseException_When_already_Product_with_that_ean() throws ServiceException {
        service.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        service.addProduct(roomijs);
    }

    @Test
    public void getProductByEan_Returns_Product_with_that_ean() throws ServiceException {
        service.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        assertEquals(roomijs, service.getProductByEan(roomijs.getEan()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProductByEan_IllegalArgumentException_When_ean_is_null() throws ServiceException {
        Long ean = null;
        service.getProductByEan(ean);
    }

    @Test(expected = ServiceException.class)
    public void getProductByEan_DatabaseException_When_no_product_with_that_ean() throws ServiceException {
        service.getProductByEan(roomijs.getEan());
    }

    @Test
    public void getProductByHope_Returns_Product_with_that_hope() throws ServiceException {
        service.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        assertEquals(roomijs, service.getProductByHope(roomijs.getHope()));
    }

    @Test(expected = ServiceException.class)
    public void getProductByHope_DatabaseException_When_no_product_with_that_hope() throws ServiceException {
        service.getProductByHope(roomijs.getHope());
    }

    @Test
    public void getAllProducts_Returns_all_products() throws ServiceException {
        assertEquals(size, service.getAllProducts().size());
    }

    @Test
    public void updateProduct_Update_info_of_the_product() throws ServiceException {
        service.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        int hope = 9876;
        String name = "nieuwe naam";
        roomijs.setHope(hope);
        roomijs.setName(name);
        service.updateProduct(roomijs);
        assertEquals(hope, service.getProductByEan(roomijs.getEan()).getHope());
        assertEquals(name, service.getProductByEan(roomijs.getEan()).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateProduct_IllegalArgumentException_If_product_is_null() throws ServiceException {
        service.addProduct(roomijs);
        productsToDeleteAfterTest.add(roomijs);
        int hope = 9876;
        String name = "nieuwe naam";
        roomijs.setHope(hope);
        roomijs.setName(name);
        service.updateProduct(null);
    }

    @Test
    public void deleteProduct_Removes_product_from_database() throws ServiceException {
        service.addProduct(roomijs);
        size = service.getNumberOfProducts();
        service.deleteProduct(roomijs.getEan());
        assertEquals(size - 1, service.getNumberOfProducts());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteProduct_IllegalArgumentException_When_ean_is_null() throws ServiceException {
        Long ean = null;
        service.deleteProduct(ean);
    }

}
