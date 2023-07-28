package com.techelevator;

import java.math.BigDecimal;

public class Munchy extends Item{
    public Munchy(BigDecimal price, String slotLocation, String productName, int stock, String dispenseMessage) {
        super(price, slotLocation, productName, stock, dispenseMessage);
    }
}
