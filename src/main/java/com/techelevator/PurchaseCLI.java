package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PurchaseCLI {
    private BigDecimal currentMoneyProvided = new BigDecimal(0);
    private BigDecimal moneyFed;
    private String change;
    private BigDecimal quarters;
    private BigDecimal dimes;
    private BigDecimal nickels;
    private final double QUARTER = 0.25;
    private final double DIME = 0.10;
    private final double NICKEL = 0.05;
    private final double BOGODO = 1.00;

    public void setMoneyFed(BigDecimal moneyFed) {
        this.moneyFed = moneyFed;
    }

    public BigDecimal getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public BigDecimal getMoneyFed() {
        return moneyFed;
    }

 //   PurchaseCLI purchaseMenu = new PurchaseCLI();

    public void run() {
        System.out.println("testing");
    }
    public BigDecimal addToCurrentMoney(BigDecimal currentMoneyProvided, BigDecimal moneyFed) {
        this.currentMoneyProvided = currentMoneyProvided.add(moneyFed);
        return this.currentMoneyProvided;
    }

    public BigDecimal subtractFromCurrentMoney(BigDecimal currentMoneyProvided, BigDecimal money) {
        this.currentMoneyProvided = currentMoneyProvided.subtract(money);
        return this.currentMoneyProvided;
    }

    public String makeChange (BigDecimal currentMoneyProvided) {
        quarters = ((currentMoneyProvided.divide(BigDecimal.valueOf(QUARTER))).setScale(0, RoundingMode.DOWN));
        dimes = ((currentMoneyProvided.subtract(quarters.multiply(BigDecimal.valueOf(QUARTER)))).divide(BigDecimal.valueOf(DIME))).setScale(0, RoundingMode.DOWN);
        nickels = ((currentMoneyProvided.subtract(quarters.multiply(BigDecimal.valueOf(QUARTER)))).subtract(dimes.multiply(BigDecimal.valueOf(DIME))).divide(BigDecimal.valueOf(NICKEL))).setScale(0, RoundingMode.DOWN);
        System.out.println("Your change is " + currentMoneyProvided + ", which will be dispensed to you as" + "\n" + quarters + " quarters, " + dimes + " dime(s), & " + nickels + " nickel(s).");
        change = "Your change is " + currentMoneyProvided + ", which will be dispensed to you as" + "\n" + quarters + " quarters, " + dimes + " dime(s), & " + nickels + " nickel(s).";
        this.currentMoneyProvided = BigDecimal.valueOf(0);
        return change;
    }

    public BigDecimal priceDiscount (BigDecimal getPrice) {
        return getPrice.subtract(BigDecimal.valueOf(BOGODO));
    }

    public BigDecimal priceReset (BigDecimal getPrice) {
        return getPrice.add(BigDecimal.valueOf(BOGODO));
    }

    //TODO format this method to update balance to 0, pull from currentmoneyprovided, and print in a user friendly way
}
