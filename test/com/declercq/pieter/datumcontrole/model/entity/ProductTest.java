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
    public void setUp() throws DomainException {
        chips = new Product(8710398016591L, 23968, "250GR LAY S BICKY CRISP");
    }

    @After
    public void tearDown() {
        chips = null;
    }

    /**
     * Test of setEan method, of class Product.
     */
    @Test
    public void setEan_Param_is_new_ean() throws DomainException {
        Long newEan = pow(10L, Product.MIN_AMOUNT_OF_DIGITS_EAN - 1);
        chips.setEan(newEan);
        assertEquals(newEan, chips.getEan());
    }

    @Test(expected = DomainException.class)
    public void setEan_DomainException_If_param_is_null() throws DomainException {
        Long newEan = null;
        chips.setEan(newEan);
    }

    @Test(expected = DomainException.class)
    public void setEan_DomainException_If_param_has_less_than_MIN_digits() throws DomainException {
        Long newEan = pow(10L, Product.MIN_AMOUNT_OF_DIGITS_EAN - 2);
        chips.setEan(newEan);
    }

    @Test(expected = DomainException.class)
    public void setEan_DomainException_If_param_has_more_than_MAX_digits() throws DomainException {
        Long newEan = pow(10L, Product.MAX_AMOUNT_OF_DIGITS_EAN);
        chips.setEan(newEan);
    }

    @Test(expected = DomainException.class)
    public void setEan_DomainException_If_param_is_negative() throws DomainException {
        Long newEan = pow(-10L, Product.MIN_AMOUNT_OF_DIGITS_EAN - 1);;
        chips.setEan(newEan);
    }

    @Test
    public void setHope_Param_is_new_hope() throws DomainException {
        int newHope = (int) Math.pow(10, Product.MIN_AMOUNT_OF_DIGITS_HOPE - 1);
        chips.setHope(newHope);
        assertEquals(newHope, chips.getHope());
    }

    @Test(expected = DomainException.class)
    public void setHope_DomainException_If_param_has_less_than_MIN_digits() throws DomainException {
        int newHope = (int) Math.pow(10, Product.MIN_AMOUNT_OF_DIGITS_HOPE - 2);
        chips.setHope(newHope);
    }

    @Test(expected = DomainException.class)
    public void setHope_DomainException_If_param_has_more_than_MAX_digits() throws DomainException {
        int newHope = (int) Math.pow(10, Product.MAX_AMOUNT_OF_DIGITS_HOPE);
        chips.setHope(newHope);
    }

    @Test(expected = DomainException.class)
    public void setHope_DomainException_If_param_is_negative() throws DomainException {
        int newHope = (int) Math.pow(-10, Product.MIN_AMOUNT_OF_DIGITS_HOPE - 1);
        chips.setHope(newHope);
    }

    @Test
    public void setName_Param_is_converted_without_linebreak_and_set_to_name() throws DomainException {
        String newName = "\nnieuwe\n productnaam1\n\r\n";
        chips.setName(newName);
        assertEquals("nieuwe productnaam1", chips.getName());
    }

    @Test
    public void setName_Param_is_trimmed_and_set_to_name() throws DomainException {
        String newName = "   nieuwe productnaam1    ";
        chips.setName(newName);
        assertEquals("nieuwe productnaam1", chips.getName());
    }

    @Test
    public void setName_Param_is_converted_to_lowercase_and_set_to_name() throws DomainException {
        String newName = "NiEUwe PrODUcTnaAm1";
        chips.setName(newName);
        assertEquals("nieuwe productnaam1", chips.getName());
    }

    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_is_null() throws DomainException {
        String newName = null;
        chips.setName(newName);
    }
    
    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_does_not_only_consists_of_alphanumeric_char() throws DomainException {
        String newName = "<?php echo lol; ?php>nieuwe productnaam1";
        chips.setName(newName);
    }

    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_If_param_has_less_than_MIN_characters() throws DomainException {
        String newName = new String(new char[Product.MIN_AMOUNT_OF_CHARACTERS_NAME - 1]).replace("\0", "a");
        chips.setName(newName);
    }

    @Test(expected = DomainException.class)
    public void setName_DomainException_If_param_If_param_has_more_than_MAX_characters() throws DomainException {
        String newName = new String(new char[Product.MAX_AMOUNT_OF_CHARACTERS_NAME + 1]).replace("\0", "a");
        chips.setName(newName);
    }

}
