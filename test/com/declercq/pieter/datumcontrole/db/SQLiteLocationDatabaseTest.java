package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.entity.Location;
import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.DomainException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Pieter
 */
public class SQLiteLocationDatabaseTest {

    private SQLiteLocationDatabase db;
    private int size;
    private List<Location> locationsToDeleteAfterTest;
    private Location gang1;

    public SQLiteLocationDatabaseTest() {
    }

    @Before
    public void setUp() throws DatabaseException, DomainException {
        db = new SQLiteLocationDatabase("jdbc:sqlite:‪DatumControle.sqlite");
        size = db.size();
        locationsToDeleteAfterTest = new ArrayList<>();
        gang1 = new Location("gang 1");
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

    @Test(expected = DatabaseException.class)
    public void addLocation_DatabaseException_When_Location_is_null() throws DatabaseException {
        gang1 = null;
        db.addLocation(gang1);
    }

    @Test(expected = DatabaseException.class)
    public void addLocation_DatabaseException_When_already_Location_with_that_name() throws DatabaseException {
        db.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        db.addLocation(gang1);
    }

    @Test
    public void deleteLocation_Removes_Location_from_database() throws DatabaseException {
        db.addLocation(gang1);
        size = db.size();
        db.deleteLocation("gang 1");
        assertEquals(size - 1, db.size());
    }

    @Test(expected = DatabaseException.class)
    public void deleteLocation_DatabaseException_When_name_is_null() throws DatabaseException {
        String name = null;
        db.deleteLocation(name);
    }
    
}