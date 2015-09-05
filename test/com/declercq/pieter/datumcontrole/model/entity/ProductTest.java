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
public class ProductTest {

    private Product chips;

    public ProductTest() {
    }

    long pow(long a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        if (isEven(b)) {
            return pow(a * a, b / 2); //even a=(a^2)^b/2
        } else {
            return a * pow(a * a, b / 2); //odd  a=a*(a^2)^b/2
        }
    }

    boolean isEven(double num) {
        return ((num % 2) == 0);
    }

    @Before
    public void setUp() {
        chips = new Product(8710398016591L, 23968, "250GR LAY S BICKY CRISP");
    }

    @After
    public void tearDown() {
        chips = null;
    }

    @Test
    public void setEan_Param_is_new_ean() {
        Long newEan = pow(10L, Product.EAN_MIN_LENGTH - 1);
        chips.setEan(newEan);
        assertEquals(newEan, chips.getEan());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEan_IllegalArgumentException_If_param_is_null() {
        Long newEan = null;
        chips.setEan(newEan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEan_IllegalArgumentException_If_param_has_less_than_MIN_digits() {
        Long newEan = pow(10L, Product.EAN_MIN_LENGTH - 2);
        chips.setEan(newEan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEan_IllegalArgumentException_If_param_has_more_than_MAX_digits() {
        Long newEan = pow(10L, Product.EAN_MAX_LENGTH);
        chips.setEan(newEan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEan_IllegalArgumentException_If_param_is_negative() {
        Long newEan = pow(-10L, Product.EAN_MIN_LENGTH - 1);
        chips.setEan(newEan);
    }

    @Test
    public void setHope_Param_is_new_hope() {
        int newHope = (int) Math.pow(10, Product.HOPE_MIN_LENGTH - 1);
        chips.setHope(newHope);
        assertEquals(newHope, chips.getHope());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setHope_IllegalArgumentException_If_param_has_less_than_MIN_digits() {
        int newHope = (int) Math.pow(10, Product.HOPE_MIN_LENGTH - 2);
        chips.setHope(newHope);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setHope_IllegalArgumentException_If_param_has_more_than_MAX_digits() {
        int newHope = (int) Math.pow(10, Product.HOPE_MAX_LENGTH);
        chips.setHope(newHope);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setHope_IllegalArgumentException_If_param_is_negative() {
        int newHope = (int) Math.pow(-10, Product.HOPE_MIN_LENGTH - 1);
        chips.setHope(newHope);
    }

    @Test
    public void setName_Param_is_converted_without_linebreak_and_set_to_name() {
        String newName = "\nnieuwe\n productnaam1\n\r\n";
        chips.setName(newName);
        assertEquals("nieuwe productnaam1", chips.getName());
    }

    @Test
    public void setName_Param_is_trimmed_and_set_to_name() {
        String newName = "   nieuwe productnaam1    ";
        chips.setName(newName);
        assertEquals("nieuwe productnaam1", chips.getName());
    }

    @Test
    public void setName_Param_is_converted_to_lowercase_and_set_to_name() {
        String newName = "NiEUwe PrODUcTnaAm1";
        chips.setName(newName);
        assertEquals("nieuwe productnaam1", chips.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_IllegalArgumentException_If_param_is_null() {
        String newName = null;
        chips.setName(newName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_IllegalArgumentException_If_param_does_not_only_consists_of_alphanumeric_char() {
        String newName = "<?php echo lol; ?php>nieuwe productnaam1";
        chips.setName(newName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_IllegalArgumentException_If_param_If_param_has_less_than_MIN_characters() {
        String newName = new String(new char[Product.NAME_MIN_LENGTH - 1]).replace("\0", "a");
        chips.setName(newName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_IllegalArgumentException_If_param_If_param_has_more_than_MAX_characters() {
        String newName = new String(new char[Product.NAME_MAX_LENGTH + 1]).replace("\0", "a");
        chips.setName(newName);
    }

}
