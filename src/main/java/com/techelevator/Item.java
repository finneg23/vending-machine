package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {

    private BigDecimal price;
    private int stockQuantity;
    private String slotLocation;
    private String productName;

    public BigDecimal getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public abstract String dispenseMessage();

}
