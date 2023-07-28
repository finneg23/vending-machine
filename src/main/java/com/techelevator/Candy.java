package com.techelevator;

import java.math.BigDecimal;
public class Candy extends Item{
    public Candy(BigDecimal price, String slotLocation, String productName, int stock, String dispenseMessage) {
        super(price, slotLocation, productName, stock, dispenseMessage);
    }

    @Override
    public void setDispenseMessage(String dispenseMessage) {
        super.setDispenseMessage("Yummy Yummy, So Sweet!");
    }

}
