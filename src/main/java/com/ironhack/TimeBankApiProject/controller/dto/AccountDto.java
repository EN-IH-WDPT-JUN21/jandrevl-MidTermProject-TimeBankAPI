package com.ironhack.TimeBankApiProject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String secretKey;
    @Min(value = 100, message = "Savings Minimum balance is 100")
    private BigDecimal minimumBalance;
    @DecimalMin(value = "0", message = "Minimum interest rate is 0")
    @DecimalMax(value = "0.5", message = "Maximum Interest Rate is 0.5")
    @Digits(integer = 1, fraction = 4)
    private BigDecimal savingsInterestRate;
    @DecimalMin(value = "0.1", message = "CreditCard Minimum Interest is 0.1")
    private BigDecimal creditCardInterestRate;
    @Max(value = 100000, message = "Maximum Credit Card Limit is 100000")
    private BigDecimal creditLimit;

}
