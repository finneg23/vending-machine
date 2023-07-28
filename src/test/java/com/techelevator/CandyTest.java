package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {
private Candy candy;
    @Before
    public void setUp() {
        this.candy = new Candy(BigDecimal.valueOf(4.25), "A3", "Snykkers", 5, "Yummy Yummy, So Sweet!");
    }

    @Test
    public void test_Candy() {

        BigDecimal actualPrice = BigDecimal.valueOf(4.25);
        String actualSlotNumber = "A3";
        String actualProductName = "Snykkers";
        int actualStock = 5;
        String actualMessage = "Yummy Yummy, So Sweet!";
        assertEquals(candy.getPrice(), actualPrice);
        assertEquals(candy.getSlotLocation(), actualSlotNumber);
        assertEquals(candy.getProductName(), actualProductName);
        assertEquals(candy.getStock(), actualStock);
        assertEquals(candy.getDispenseMessage(), actualMessage);

    }
}