package com.declercq.pieter.datumcontrole.model.entity;

import com.declercq.pieter.datumcontrole.model.exception.DomainException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pieter Declercq
 * @version 3.0
 */
public class CategoryTest {

    private Category voeding;

    public CategoryTest() {
    }

    @Before
    public void setUp() throws DomainException {
        voeding = new Category("voeding", 11, "#ffa135");
    }

    @After
    public void tearDown() {
        voeding = null;
    }

    @Test
    public void setName_Param_is_converted_without_linebreak_and_set_to_name() throws DomainException {
        String newName = "\nnieuwe\n categorienaam\n\r\n";
        voeding.setName(newName);
        assertEquals("nieuwe categorienaam", voeding.getName());
    }

    @Test
    public void setName_Param_is_trimmed_and_set_to_name() throws DomainException {
        String newName = "   nieuwe categorienaam    ";
        voeding.setName(newName);
        assertEquals("nieuwe categorienaam", voeding.getName());
    }

    @Test
    public void setName_Param_is_converted_to_lowercase_and_set_to_name() throws DomainException {
        String newName = "NiEUwe CaTEGoRienaAm";
        voeding.setName(newName);
        assertEquals("nieuwe categorienaam", voeding.getName());
    }

    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_is_null() throws DomainException {
        String newName = null;
        voeding.setName(newName);
    }
    
    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_does_not_only_consists_of_alphanumeric_char() throws DomainException {
        String newName = "<?php echo lol; ?php>nieuwe categorienaam";
        voeding.setName(newName);
    }

    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_If_param_has_less_than_MIN_characters() throws DomainException {
        String newName = new String(new char[Category.MIN_AMOUNT_OF_CHARACTERS_NAME - 1]).replace("\0", "a");
        voeding.setName(newName);
    }

    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_If_param_has_more_than_MAX_characters() throws DomainException {
        String newName = new String(new char[Category.MAX_AMOUNT_OF_CHARACTERS_NAME + 1]).replace("\0", "a");
        voeding.setName(newName);
    }

    @Test
    public void setSublocations_Param_is_new_Sublocations() throws DomainException {
        int sublocations = Category.MIN_AMOUNT_OF_SUBLOCATIONS;
        voeding.setSublocations(sublocations);
        assertEquals(sublocations, voeding.getSublocations());
    }

    @Test(expected = DomainException.class)
    public void setSublocations_DomainException_If_param_has_less_than_MIN_digits() throws DomainException {
        int sublocations = Category.MIN_AMOUNT_OF_SUBLOCATIONS - 1;
        voeding.setSublocations(sublocations);
    }

    @Test(expected = DomainException.class)
    public void setSublocations_DomainException_If_param_has_more_than_MAX_digits() throws DomainException {
        int sublocations = Category.MAX_AMOUNT_OF_SUBLOCATIONS + 1;
        voeding.setSublocations(sublocations);
    }

    @Test
    public void setColor_Param_is_new_color() throws DomainException {
        String newColor = "#000000";
        voeding.setColor(newColor);
        assertEquals(newColor, voeding.getColor());
    }

    @Test(expected = DomainException.class)
    public void setColor_DomainException_If_param_is_null() throws DomainException {
        String newColor = null;
        voeding.setColor(newColor);
    }

    @Test(expected = DomainException.class)
    public void setColor_DomainException_If_param_is_not_in_hexadecimal_format() throws DomainException {
        String newColor = "bruin";
        voeding.setColor(newColor);
    }

}