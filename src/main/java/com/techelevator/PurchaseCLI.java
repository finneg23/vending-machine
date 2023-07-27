package com.techelevator;

import java.math.BigDecimal;

public class PurchaseCLI extends VendingMachineCLI{
    private BigDecimal currentMoneyProvided = new BigDecimal(0);
    private BigDecimal moneyFed;

    public BigDecimal getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public BigDecimal getMoneyFed() {
        return moneyFed;
    }

    PurchaseCLI purchaseMenu = new PurchaseCLI();

    public void run() {
        System.out.println("testing");
    }
}
