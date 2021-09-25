package com.ironhack.TimeBankApiProject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ThirdPartyTransactionDto {

    private Long account;
    private BigDecimal amount;
    private String accountSecretKey;



}
