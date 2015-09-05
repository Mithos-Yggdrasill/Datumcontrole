package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.entity.Location;
import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import java.util.Collection;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public interface LocationRepository {
    
    public int size() throws DatabaseException;
    
    public void addLocation(Location location) throws DatabaseException;
    
    public Location getLocation(String name) throws DatabaseException;
        
    public Collection<Location> getAllLocations() throws DatabaseException;
    
    public void updateLocation(Location location) throws DatabaseException;
    
    public void deleteLocation(String name) throws DatabaseException;

}
