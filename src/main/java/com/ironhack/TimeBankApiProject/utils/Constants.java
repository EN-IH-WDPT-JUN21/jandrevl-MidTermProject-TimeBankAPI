package com.ironhack.TimeBankApiProject.utils;

import java.math.BigDecimal;

public class Constants {

    public static final BigDecimal penaltyFee = new BigDecimal("40");
    public static final BigDecimal monthlyMaintenanceFee = new BigDecimal("12");
    public static final BigDecimal defaultSavingsInterest = new BigDecimal("0.0025");
    public static final BigDecimal defaultSavingsMinimumBalance = new BigDecimal("1000");
    public static final BigDecimal checkingAccountMinimumBalance = new BigDecimal("250");
    public static final BigDecimal defaultCreditCardLimit = new BigDecimal("100");
    public static final BigDecimal defaultCreditCardInterest = new BigDecimal("0.2");

    private Constants() {
        throw new AssertionError();
    }
}
