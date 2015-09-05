package com.declercq.pieter.datumcontrole.model.service;

import com.declercq.pieter.datumcontrole.db.LocationRepository;
import com.declercq.pieter.datumcontrole.model.entity.Location;
import com.declercq.pieter.datumcontrole.model.exception.db.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.ServiceException;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class LocationManager {

    private LocationRepository db;
    
    public LocationManager(LocationRepository db){
        this.db = db;
    }
    
    public int getNumberOfLocations() throws ServiceException {
        try {
            return db.size();
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }
    
    public void addLocation(Location location) throws ServiceException{
        try {
            db.addLocation(location);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }
    
    public Location getLocation(String name) throws ServiceException {
        try {
            return db.getLocation(name);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }
         
    public Collection<Location> getAllLocations() throws ServiceException{
        try {
            return db.getAllLocations();
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }
    
    public void updateLocation(Location location) throws ServiceException{
        try {
            db.updateLocation(location);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }
    
    public void deleteLocation(String name) throws ServiceException{
        try {
            db.deleteLocation(name);
        } catch (DatabaseException ex) {
            throw new ServiceException(ex);
        }
    }
    
}
