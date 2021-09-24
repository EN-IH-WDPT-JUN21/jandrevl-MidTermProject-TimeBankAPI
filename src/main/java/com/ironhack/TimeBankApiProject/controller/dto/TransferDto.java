package com.ironhack.TimeBankApiProject.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {

    private Long originAccountNumber;
    private Long beneficiaryAccountNumber;
    @Size(min = 3)
    private String beneficiaryName;
    @Min(value = 0, message = "Amount cannot be negative")
    private BigDecimal amount;
}
