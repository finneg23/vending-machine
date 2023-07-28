package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PurchaseCLITest {
    private PurchaseCLI purchaseCLI;
    @Before
    public void setUp() {
        this.purchaseCLI = new PurchaseCLI();
    }

    @Test
    public void makeChange() {

        String expectedResult = "Your change is " + BigDecimal.valueOf(4.65) + ", which will be dispensed to you as" + "\n" + 18 + " quarters, " + 1 + " dime(s), & " + 1 + " nickel(s).";
        String actualResult = purchaseCLI.makeChange(BigDecimal.valueOf(4.65));

        assertEquals(expectedResult, actualResult);

    }
}