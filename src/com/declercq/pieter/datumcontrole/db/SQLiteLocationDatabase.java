package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.entity.Location;
import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class SQLiteLocationDatabase implements LocationDatabase {

    private Connection connection;
    private PreparedStatement statement;
    String url;

    public SQLiteLocationDatabase(String url) throws DatabaseException {
        try {
            Class.forName("org.sqlite.JDBC");
            this.url = url;
        } catch (ClassNotFoundException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_DRIVER_NOT_LOADED, e);
        }
    }

    @Override
    public int size() throws DatabaseException {
        String query = "SELECT COUNT(name) AS size FROM location";
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
    public void addLocation(Location location) throws DatabaseException {
        if (location == null) {
            throw new DatabaseException(ErrorMessages.LOCATION_NULL);
        }
        String query = "INSERT INTO location (name) VALUES (?)";
        initiateStatement(query);
        try {
            statement.setString(1, location.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.LOCATION_ALREADY_EXISTS, e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Location getLocation(String name) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Location> getAllLocations() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLocation(Location location) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLocation(String name) throws DatabaseException {
        if (name == null) {
            throw new DatabaseException(ErrorMessages.NAME_NULL);
        }
        String query = "DELETE FROM location WHERE name = ?";
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
