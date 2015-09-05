package com.declercq.pieter.datumcontrole.model.entity;

import com.declercq.pieter.datumcontrole.model.exception.DomainException;
import com.declercq.pieter.datumcontrole.model.exception.ErrorMessages;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public abstract class Identifier {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws DomainException {
        if(id == null){
            throw new DomainException(ErrorMessages.ID_NULL);
        }
        this.id = id;
    }

}
