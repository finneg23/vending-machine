package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinkTest {
    private Drink drink;

    @Before
    public void setup() {
        this.drink = new Drink(BigDecimal.valueOf(1.65), "A2", "Ginger Ayle", 5, "Glug Glug, Yum");
    }
    @Test
    public void test_Drink() {


    }


}