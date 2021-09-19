package com.ironhack.TimeBankApiProject.utils;

import java.math.BigDecimal;

public class Constants {

    public static final BigDecimal penaltyFee = new BigDecimal("40");
    public static final BigDecimal monthlyMaintenanceFee = new BigDecimal("12");

    private Constants() {
        throw new AssertionError();
    }
}
