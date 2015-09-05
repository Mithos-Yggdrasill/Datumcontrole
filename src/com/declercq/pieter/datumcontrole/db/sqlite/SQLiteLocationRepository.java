package com.declercq.pieter.datumcontrole.db.sqlite;

import com.declercq.pieter.datumcontrole.db.LocationRepository;
import com.declercq.pieter.datumcontrole.model.entity.Location;
import com.declercq.pieter.datumcontrole.model.exception.db.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;
import com.declercq.pieter.datumcontrole.model.exception.db.LocationAlreadyExistsException;
import com.declercq.pieter.datumcontrole.model.exception.db.LocationNotFoundException;
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
public class SQLiteLocationRepository implements LocationRepository {

    private Connection connection;
    private PreparedStatement statement;
    String url;

    public SQLiteLocationRepository(String url) throws DatabaseException {
        this.url = url;
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
            throw new IllegalArgumentException(ErrorMessages.LOCATION_NULL);
        }
        String query = "INSERT INTO location (name) VALUES (?)";
        initiateStatement(query);
        try {
            statement.setString(1, location.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new LocationAlreadyExistsException(ErrorMessages.LOCATION_ALREADY_EXISTS, e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Location getLocation(String name) throws DatabaseException {
        if (name == null) {
            throw new IllegalArgumentException(ErrorMessages.NAME_NULL);
        }
        String query = "SELECT * FROM location WHERE name = ?";
        Location location = null;
        initiateStatement(query);
        try {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                location = new Location(name);
            }
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } finally {
            closeConnection();
        }
        if (location == null) {
            throw new LocationNotFoundException(ErrorMessages.LOCATION_NOT_FOUND);
        }
        return location;
    }

    @Override
    public Collection<Location> getAllLocations() throws DatabaseException {
        String query = "SELECT * FROM location";
        Collection<Location> locations = new ArrayList<>();
        initiateStatement(query);
        try {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                Location location = new Location(name);
                locations.add(location);
            }
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } finally {
            closeConnection();
        }
        return locations;
    }

    @Override
    public void updateLocation(Location location) throws DatabaseException {
        if (location == null) {
            throw new IllegalArgumentException(ErrorMessages.LOCATION_NULL);
        }
        String query = "UPDATE location SET name = ? WHERE name = ?";
        initiateStatement(query);
        try {
            statement.setString(1, location.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException(ErrorMessages.DATABASE_FAULT_IN_QUERY, e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deleteLocation(String name) throws DatabaseException {
        if (name == null) {
            throw new IllegalArgumentException(ErrorMessages.NAME_NULL);
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
