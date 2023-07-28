package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Item{
    public Drink(BigDecimal price, String slotLocation, String productName, int stock, String dispenseMessage) {
        super(price, slotLocation, productName, stock, dispenseMessage);
    }


}
