package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {

    private BigDecimal price;
    private String slotLocation;
    private String productName;
    private int stock = 5;

    public void setDispenseMessage(String dispenseMessage) {
        this.dispenseMessage = dispenseMessage;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public String getSlotLocation() {
        return slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public int getStock() {
        return stock;
    }

    public String getDispenseMessage() {
        return dispenseMessage;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private String dispenseMessage;

    public Item(BigDecimal price, String slotLocation, String productName, int stock, String dispenseMessage) {
        this.price = price;
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.stock = stock;
        this.dispenseMessage = dispenseMessage;
    }

    public String dispense(){
        System.out.println(getProductName() + " $" + getPrice());
        return getDispenseMessage();
    }

    public int takeOutOfStock () {
        this.stock--;
        return this.stock;
    }
    //print the dispense message
    //subtract the amount from the current money
        //check to see if another product has been purchased for discounting
    //update the quantity(stock)
    //sout get name, get cost, get current money provided, get message
}
