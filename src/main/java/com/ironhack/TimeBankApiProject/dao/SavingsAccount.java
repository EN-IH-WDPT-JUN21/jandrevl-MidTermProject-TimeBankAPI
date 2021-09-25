package com.ironhack.TimeBankApiProject.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavingsAccount extends Account{

    @Column(name = "savings_interest_rate", columnDefinition = "DECIMAL(5,5)")
    @Positive
    private BigDecimal interestRate;

    //Constructor for checking accounts with 2 owners, default minimum Balance, default interestRate
    public SavingsAccount (User primaryOwner, User secondaryOwner,
                            String secretKey) {
        super(primaryOwner, secondaryOwner, secretKey);
        this.minimumBalance = new BigDecimal("1000");
        this.interestRate = new BigDecimal("0.0025");
    }

    //Constructor for checking accounts with only 1 owner
    public SavingsAccount (User primaryOwner,
                            String secretKey) {
        super(primaryOwner, secretKey);
        this.minimumBalance = new BigDecimal("1000");
        this.interestRate = new BigDecimal("0.0025");
    }

    //Constructor for checking accounts with 2 owners and custom interest and minimumBalance
    public SavingsAccount (User primaryOwner, User secondaryOwner,
                           String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);

    }

    //Constructor for checking accounts with 1 owner and custom interest and minimumBalance
    public SavingsAccount (User primaryOwner,
                           String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);

    }
}
