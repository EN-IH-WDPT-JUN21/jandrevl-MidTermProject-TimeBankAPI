package com.ironhack.TimeBankApiProject.dao;


import com.ironhack.TimeBankApiProject.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "primary_owner")
    @NotNull
    private AccountHolder primaryOwner;

    @Column(name = "secondary_owner")
    private AccountHolder secondaryOwner;

    private BigDecimal balance;

    @Column(name = "penalty_fee")
    private final BigDecimal penaltyFee = BigDecimal.valueOf(40);

    @Column(name = "minimum_balance")
    private BigDecimal minimumBalance;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "account_status")
    private AccountStatus status;
}
