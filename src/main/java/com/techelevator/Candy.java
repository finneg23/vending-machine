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
    public String dispense(){
        System.out.println(getProductName() + " $" + getPrice());
        return getDispenseMessage();
    }
 //   public int takeOutOfStock () {
 //       this.stock--;
  //      return this.stock;
  //  }

}
