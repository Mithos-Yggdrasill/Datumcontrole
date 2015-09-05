package com.declercq.pieter.datumcontrole.model.entity;

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
    public void setUp() {
        voeding = new Category("voeding", 11, "#ffa135");
    }

    @After
    public void tearDown() {
        voeding = null;
    }

    @Test
    public void setName_Param_is_converted_without_linebreak_and_set_to_name() {
        String newName = "\nnieuwe\n categorienaam\n\r\n";
        voeding.setName(newName);
        assertEquals("nieuwe categorienaam", voeding.getName());
    }

    @Test
    public void setName_Param_is_trimmed_and_set_to_name() {
        String newName = "   nieuwe categorienaam    ";
        voeding.setName(newName);
        assertEquals("nieuwe categorienaam", voeding.getName());
    }

    @Test
    public void setName_Param_is_converted_to_lowercase_and_set_to_name() {
        String newName = "NiEUwe CaTEGoRienaAm";
        voeding.setName(newName);
        assertEquals("nieuwe categorienaam", voeding.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_IllegalArgumentException_If_param_is_null() {
        String newName = null;
        voeding.setName(newName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_IllegalArgumentException_If_param_does_not_only_consists_of_alphanumeric_char() {
        String newName = "<?php echo lol; ?php>nieuwe categorienaam";
        voeding.setName(newName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_IllegalArgumentException_If_param_If_param_has_less_than_MIN_characters() {
        String newName = new String(new char[Category.NAME_MIN_LENGTH - 1]).replace("\0", "a");
        voeding.setName(newName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_IllegalArgumentException_If_param_If_param_has_more_than_MAX_characters() {
        String newName = new String(new char[Category.NAME_MAX_LENGTH + 1]).replace("\0", "a");
        voeding.setName(newName);
    }

    @Test
    public void setSublocations_Param_is_new_Sublocations() {
        int sublocations = Category.SUBLOCATIONS_MIN_AMOUNT;
        voeding.setSublocations(sublocations);
        assertEquals(sublocations, voeding.getSublocations());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setSublocations_IllegalArgumentException_If_param_has_less_than_MIN_digits() {
        int sublocations = Category.SUBLOCATIONS_MIN_AMOUNT - 1;
        voeding.setSublocations(sublocations);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setSublocations_IllegalArgumentException_If_param_has_more_than_MAX_digits() {
        int sublocations = Category.SUBLOCATIONS_MAX_AMOUNT + 1;
        voeding.setSublocations(sublocations);
    }

    @Test
    public void setColor_Param_is_new_color() {
        String newColor = "#000000";
        voeding.setColor(newColor);
        assertEquals(newColor, voeding.getColor());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setColor_IllegalArgumentException_If_param_is_null() {
        String newColor = null;
        voeding.setColor(newColor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setColor_IllegalArgumentException_If_param_is_not_in_hexadecimal_format() {
        String newColor = "bruin";
        voeding.setColor(newColor);
    }

}
