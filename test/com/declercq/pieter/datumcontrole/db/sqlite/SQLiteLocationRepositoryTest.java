package com.declercq.pieter.datumcontrole.db.sqlite;

import com.declercq.pieter.datumcontrole.model.entity.Location;
import com.declercq.pieter.datumcontrole.model.exception.db.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.db.LocationAlreadyExistsException;
import com.declercq.pieter.datumcontrole.model.exception.db.LocationNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class SQLiteLocationRepositoryTest {
    
    private SQLiteLocationRepository db;
    private int size;
    private List<Location> locationsToDeleteAfterTest;
    private Location gang1;
    
    public SQLiteLocationRepositoryTest() {
    }
    
    @Before
    public void setUp() throws DatabaseException {
        db = new SQLiteLocationRepository("jdbc:sqlite:‪DatumControle.sqlite");
        size = db.size();
        locationsToDeleteAfterTest = new ArrayList<>();
        gang1 = new Location();
        gang1.setName("gang 1");
    }
    
    @After
    public void tearDown() throws DatabaseException {
        for (Location l : locationsToDeleteAfterTest) {
            db.deleteLocation(l.getName());
        }
        db = null;
        size = 0;
        locationsToDeleteAfterTest = null;
        gang1 = null;
    }
    
    @Test
    public void addLocation_Adds_Location_to_database() throws DatabaseException {
        db.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        assertEquals(size + 1, db.size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addLocation_IllegalArgumentException_When_Location_is_null() throws DatabaseException {
        gang1 = null;
        db.addLocation(gang1);
    }
    
    @Test(expected = LocationAlreadyExistsException.class)
    public void addLocation_LocationAlreadyExistsException_When_already_Location_with_that_name() throws DatabaseException {
        db.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        db.addLocation(gang1);
    }
    
    @Test
    public void getLocation_Returns_Location_with_that_name() throws DatabaseException {
        db.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        assertEquals(gang1, db.getLocation(gang1.getName()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getLocation_IllegalArgumentException_When_name_is_null() throws DatabaseException {
        String name = null;
        db.getLocation(name);
    }
    
    @Test(expected = LocationNotFoundException.class)
    public void getLocation_LocationNotFoundException_When_no_location_with_that_name() throws DatabaseException {
        db.getLocation(gang1.getName());
    }
    
    @Test
    public void getAllLocations_Returns_all_locations() throws DatabaseException {
        assertEquals(size, db.getAllLocations().size());
    }
    
    @Test
    public void updateLocation_Update_info_of_the_Location() throws DatabaseException {
        db.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        db.updateLocation(gang1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateProduct_IllegalArgumentException_If_product_is_null() throws DatabaseException {
        db.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        db.updateLocation(null);
    }
    
    @Test
    public void deleteLocation_Removes_Location_from_database() throws DatabaseException {
        db.addLocation(gang1);
        size = db.size();
        db.deleteLocation("gang 1");
        assertEquals(size - 1, db.size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deleteLocation_IllegalArgumentException_When_name_is_null() throws DatabaseException {
        String name = null;
        db.deleteLocation(name);
    }
    
}
