package com.techelevator;

import java.math.BigDecimal;

public class PurchaseCLI {
    private BigDecimal currentMoneyProvided = new BigDecimal(0);
    private BigDecimal moneyFed;

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
    public BigDecimal updateCurrentMoney(BigDecimal currentMoneyProvided, BigDecimal moneyFed) {
        currentMoneyProvided = currentMoneyProvided.add(moneyFed);
        return currentMoneyProvided;
    }
}
