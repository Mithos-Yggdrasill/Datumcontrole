package com.declercq.pieter.datumcontrole.db.sqlite;

import com.declercq.pieter.datumcontrole.db.ProductRepository;
import com.declercq.pieter.datumcontrole.model.entity.Product;
import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.DomainException;
import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class SQLiteProductRepository implements ProductRepository {

    private Connection connection;
    private PreparedStatement statement;
    String url;

    public SQLiteProductRepository(String url) throws DatabaseException {
        try {
            Class.forName("org.sqlite.JDBC");
            this.url = url;
        } catch (ClassNotFoundException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_DRIVER_NOT_LOADED, e);
        }
    }

    @Override
    public int size() throws DatabaseException {
        String query = "SELECT COUNT(ean) AS size FROM product";
        int size = 0;
        initiateStatement(query);
        try {
            ResultSet r = statement.executeQuery();
            r.next();
            size = r.getInt("size");
        } catch (SQLException ex) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, ex);
        } finally {
            closeConnection();
        }
        return size;
    }

    @Override
    public void addProduct(Product product) throws DatabaseException {
        if (product == null) {
            throw new DatabaseException(ErrorMessages.PRODUCT_NULL);
        }
        String query = "INSERT INTO product (ean, hope, name) VALUES (?, ?, ?)";
        initiateStatement(query);
        try {
            statement.setLong(1, product.getEan());
            statement.setInt(2, product.getHope());
            statement.setString(3, product.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.PRODUCT_ALREADY_EXISTS, e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Product getProductByEan(Long ean) throws DatabaseException {
        if (ean == null) {
            throw new DatabaseException(ErrorMessages.PRODUCT_EAN_NULL);
        }
        String query = "SELECT * FROM product WHERE ean = ?";
        Product product = null;
        initiateStatement(query);
        try {
            statement.setLong(1, ean);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int hope = result.getInt("hope");
                String name = result.getString("name");
                product = new Product(ean, hope, name);
            }
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } catch (DomainException ex) {
            throw new DatabaseException(ex);
        } finally {
            closeConnection();
        }
        if (product == null) {
            throw new DatabaseException(ErrorMessages.PRODUCT_NOT_FOUND_EAN);
        }
        return product;
    }

    @Override
    public Product getProductByHope(int hope) throws DatabaseException {
        String query = "SELECT * FROM product WHERE hope = ?";
        Product product = null;
        initiateStatement(query);
        try {
            statement.setInt(1, hope);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Long ean = result.getLong("ean");
                String name = result.getString("name");
                product = new Product(ean, hope, name);
            }
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } catch (DomainException ex) {
            throw new DatabaseException(ex);
        } finally {
            closeConnection();
        }
        if (product == null) {
            throw new DatabaseException(ErrorMessages.PRODUCT_NOT_FOUND_HOPE);
        }
        return product;
    }

    @Override
    public Collection<Product> getAllProducts() throws DatabaseException {
        String query = "SELECT * FROM product";
        Collection<Product> products = new ArrayList<>();
        initiateStatement(query);
        try {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Long ean = result.getLong("ean");
                int hope = result.getInt("hope");
                String name = result.getString("name");
                Product product = new Product(ean, hope, name);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } catch (DomainException ex) {
            throw new DatabaseException(ex);
        } finally {
            closeConnection();
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) throws DatabaseException {
        if (product == null) {
            throw new DatabaseException(ErrorMessages.PRODUCT_NULL);
        }
        String query = "UPDATE product SET hope = ?, name = ? WHERE ean = ?";
        initiateStatement(query);
        try {
            statement.setInt(1, product.getHope());
            statement.setString(2, product.getName());
            statement.setLong(3, product.getEan());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deleteProduct(Long ean) throws DatabaseException {
        if (ean == null) {
            throw new DatabaseException(ErrorMessages.PRODUCT_EAN_NULL);
        }
        String query = "DELETE FROM product WHERE ean = ?";
        initiateStatement(query);
        try {
            statement.setLong(1, ean);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } finally {
            closeConnection();
        }
    }

    private void initiateStatement(String query) throws DatabaseException {
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.prepareStatement(query);
        } catch (SQLException ex) {
            throw new DatabaseException(ErrorMessages.DATABASE_NOT_FOUND, ex);
        }
    }

    private void closeConnection() throws DatabaseException {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_CLOSSING_CONNECTION, e);
        }
    }
}