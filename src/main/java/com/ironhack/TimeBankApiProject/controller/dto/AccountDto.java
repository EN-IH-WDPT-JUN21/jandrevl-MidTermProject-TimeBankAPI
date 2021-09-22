package com.ironhack.TimeBankApiProject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String secretKey;
    private BigDecimal minimumBalance;
    private BigDecimal interestRate;
    private BigDecimal creditLimit;

}
