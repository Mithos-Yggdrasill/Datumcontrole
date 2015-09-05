package com.declercq.pieter.datumcontrole.db.sqlite;

import com.declercq.pieter.datumcontrole.db.CategoryRepository;
import com.declercq.pieter.datumcontrole.model.entity.Category;
import com.declercq.pieter.datumcontrole.model.exception.db.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;
import com.declercq.pieter.datumcontrole.model.exception.db.CategoryAlreadyExistsException;
import com.declercq.pieter.datumcontrole.model.exception.db.CategoryNotFoundException;
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
public class SQLiteCategoryRepository implements CategoryRepository {

    private Connection connection;
    private PreparedStatement statement;
    String url;

    public SQLiteCategoryRepository(String url) throws DatabaseException {
        this.url = url;
    }

    @Override
    public int size() throws DatabaseException {
        String query = "SELECT COUNT(name) AS size FROM category";
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
    public void addCategory(Category category) throws DatabaseException {
        if (category == null) {
            throw new IllegalArgumentException(ErrorMessages.CATEGORY_NULL);
        }
        String query = "INSERT INTO category (name, sublocations, color) VALUES (?, ?, ?)";
        initiateStatement(query);
        try {
            statement.setString(1, category.getName());
            statement.setInt(2, category.getSublocations());
            statement.setString(3, category.getColor());
            statement.execute();
        } catch (SQLException e) {
            throw new CategoryAlreadyExistsException(ErrorMessages.CATEGORY_ALREADY_EXISTS, e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Category getCategory(String name) throws DatabaseException {
        if (name == null) {
            throw new IllegalArgumentException(ErrorMessages.NAME_NULL);
        }
        String query = "SELECT * FROM category WHERE name = ?";
        Category category = null;
        initiateStatement(query);
        try {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                category = new Category();
                category.setName(result.getString("name"));
                category.setSublocations(result.getInt("sublocations"));
                category.setColor(result.getString("color"));
            }
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } finally {
            closeConnection();
        }
        if (category == null) {
            throw new CategoryNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND);
        }
        return category;
    }

    @Override
    public Collection<Category> getAllCategories() throws DatabaseException {
        String query = "SELECT * FROM category";
        Collection<Category> categories = new ArrayList<>();
        initiateStatement(query);
        try {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Category category = new Category();
                category.setName(result.getString("name"));
                category.setSublocations(result.getInt("sublocations"));
                category.setColor(result.getString("color"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } finally {
            closeConnection();
        }
        return categories;
    }

    @Override
    public void updateCategory(Category category) throws DatabaseException {
        if (category == null) {
            throw new IllegalArgumentException(ErrorMessages.CATEGORY_NULL);
        }
        String query = "UPDATE category SET sublocations = ?, color = ? WHERE name = ?";
        initiateStatement(query);
        try {
            statement.setInt(1, category.getSublocations());
            statement.setString(2, category.getColor());
            statement.setString(3, category.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deleteCategory(String name) throws DatabaseException {
        if (name == null) {
            throw new IllegalArgumentException(ErrorMessages.NAME_NULL);
        }
        String query = "DELETE FROM category WHERE name = ?";
        initiateStatement(query);
        try {
            statement.setString(1, name);
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
