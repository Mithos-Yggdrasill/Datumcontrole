package com.declercq.pieter.datumcontrole.db;

import com.declercq.pieter.datumcontrole.model.exception.DatabaseException;
import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class RepositoryFactory {

    public static Repository createDatabase(String dbType) throws DatabaseException {
        if(dbType.equals("sqlite")){
            return new SQLiteDatabase("jdbc:sqlite:‪DatumControle.sqlite");
        }
        throw new DatabaseException(ErrorMessages.DATABASETYPE_NOT_SUPPORTED);
    }
    
}
