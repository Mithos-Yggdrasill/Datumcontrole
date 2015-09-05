package com.declercq.pieter.datumcontrole.model.service;

import com.declercq.pieter.datumcontrole.db.SQLiteLocationDatabase;
import com.declercq.pieter.datumcontrole.model.entity.Location;
import com.declercq.pieter.datumcontrole.model.exception.DomainException;
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
public class LocationManagerTest {
    
    private LocationManager service;
    private int size;
    private List<Location> locationsToDeleteAfterTest;
    private Location gang1;
    
    public LocationManagerTest() {
    }
    
    @Before
    public void setUp() throws Exception {
        service = new LocationManager(new SQLiteLocationDatabase("jdbc:sqlite:‪DatumControle.sqlite"));
        size = service.getNumberOfLocations();
        locationsToDeleteAfterTest = new ArrayList<>();
        gang1 = new Location("gang 1");
    }
    
    @After
    public void tearDown() throws Exception {
        for (Location l : locationsToDeleteAfterTest) {
            service.deleteLocation(l.getName());
        }
        service = null;
        size = 0;
        locationsToDeleteAfterTest = null;
        gang1 = null;
    }

    @Test
    public void addLocation_Adds_Location_to_database() throws ServiceException {
        service.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        assertEquals(size + 1, service.getNumberOfLocations());
    }

    @Test(expected = ServiceException.class)
    public void addLocation_ServiceException_When_Location_is_null() throws ServiceException {
        gang1 = null;
        service.addLocation(gang1);
    }

    @Test(expected = ServiceException.class)
    public void addLocation_ServiceException_When_already_Location_with_that_name() throws ServiceException {
        service.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        service.addLocation(gang1);
    }

    @Test
    public void getLocation_Returns_Location_with_that_name() throws ServiceException {
        service.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        assertEquals(gang1, service.getLocation(gang1.getName()));
    }

    @Test(expected = ServiceException.class)
    public void getLocation_ServiceException_When_name_is_null() throws ServiceException {
        String name = null;
        service.getLocation(name);
    }

    @Test(expected = ServiceException.class)
    public void getLocation_ServiceException_When_no_location_with_that_name() throws ServiceException {
        service.getLocation(gang1.getName());
    }

    @Test
    public void getAllLocations_Returns_all_locations() throws ServiceException {
        assertEquals(size, service.getAllLocations().size());
    }

    @Test
    public void updateLocation_Update_info_of_the_Location() throws ServiceException, DomainException {
        service.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        service.updateLocation(gang1);
    }

    @Test(expected = ServiceException.class)
    public void updateProduct_ServiceException_If_product_is_null() throws ServiceException, DomainException {
        service.addLocation(gang1);
        locationsToDeleteAfterTest.add(gang1);
        service.updateLocation(null);
    }

    @Test
    public void deleteLocation_Removes_Location_from_database() throws ServiceException {
        service.addLocation(gang1);
        size = service.getNumberOfLocations();
        service.deleteLocation("gang 1");
        assertEquals(size - 1, service.getNumberOfLocations());
    }

    @Test(expected = ServiceException.class)
    public void deleteLocation_ServiceException_When_name_is_null() throws ServiceException {
        String name = null;
        service.deleteLocation(name);
    }
    
}