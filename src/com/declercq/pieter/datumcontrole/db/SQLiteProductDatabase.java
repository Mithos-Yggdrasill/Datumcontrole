package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.entity.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class SQLiteProductDatabase implements IProductDatabase {

    private Connection connection;
    private PreparedStatement statement;

    public SQLiteProductDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:‪DatumControle.sqlite");
        } catch (ClassNotFoundException e) {
            System.out.println(e); //TODO: correct exception handling
        } catch (SQLException e) {
            System.out.println(e); //TODO: correct exception handling
        }
    }

    @Override
    public void addProduct(Product product) {
        //TODO defensive programming
        String query = "INSERT INTO product (ean, hope, name) VALUES (?, ?, ?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, product.getEan());
            statement.setInt(2, product.getHope());
            statement.setString(3, product.getName());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e); //TODO: correct exception handling
        } finally {
            closeConnection();
        }

    }

    @Override
    public Product getProduct(Long ean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProduct(int hope) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Product> getAllProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduct(Long ean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void closeConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e); //TODO: correct exception handling        }
        }

    }
}
