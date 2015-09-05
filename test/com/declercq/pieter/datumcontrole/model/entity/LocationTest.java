package com.declercq.pieter.datumcontrole.model.entity;

import com.declercq.pieter.datumcontrole.model.exception.DomainException;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class LocationTest {

    private Location gang1;

    public LocationTest() {

    }

    @Before
    public void setUp() throws DomainException {
        gang1 = new Location("gang 1");
    }

    @After
    public void tearDown() {
        gang1 = null;
    }

    @Test
    public void setName_Param_is_converted_without_linebreak_and_set_to_name() throws DomainException {
        String newName = "\ngang 2\n mayonaisekant\n\r\n";
        gang1.setName(newName);
        assertEquals("gang 2 mayonaisekant", gang1.getName());
    }

    @Test
    public void setName_Param_is_trimmed_and_set_to_name() throws DomainException {
        String newName = "   gang 2 mayonaisekant    ";
        gang1.setName(newName);
        assertEquals("gang 2 mayonaisekant", gang1.getName());
    }

    @Test
    public void setName_Param_is_converted_to_lowercase_and_set_to_name() throws DomainException {
        String newName = "gAng 2 mAYoNAIsekaNT";
        gang1.setName(newName);
        assertEquals("gang 2 mayonaisekant", gang1.getName());
    }

    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_is_null() throws DomainException {
        String newName = null;
        gang1.setName(newName);
    }

    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_does_not_only_consists_of_alphanumeric_char() throws DomainException {
        String newName = "<?php echo lol; ?php>nieuwe productnaam1";
        gang1.setName(newName);
    }

    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_If_param_has_less_than_MIN_characters() throws DomainException {
        String newName = new String(new char[Location.MIN_AMOUNT_OF_CHARACTERS_NAME - 1]).replace("\0", "a");
        gang1.setName(newName);
    }

    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_If_param_has_more_than_MAX_characters() throws DomainException {
        String newName = new String(new char[Location.MAX_AMOUNT_OF_CHARACTERS_NAME + 1]).replace("\0", "a");
        gang1.setName(newName);
    }

}