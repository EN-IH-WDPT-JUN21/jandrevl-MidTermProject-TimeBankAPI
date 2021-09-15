package com.ironhack.TimeBankApiProject.dao;

import com.ironhack.TimeBankApiProject.utils.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class CreditCardAccount extends Account{

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    //Constructor for checking accounts with 2 owners, default creditLimit, default interestRate
    public CreditCardAccount (AccountHolder primaryOwner, AccountHolder secondaryOwner,
                           String secretKey) {
        super(primaryOwner, secondaryOwner, secretKey);
        this.minimumBalance = new BigDecimal("0");
        this.interestRate = new BigDecimal("0.2");
        this.creditLimit = new BigDecimal("100");
        setBalance(new Money(this.creditLimit));
    }

    //Constructor for checking accounts with only 1 owner, default creditLimit, default interestRate
    public CreditCardAccount (AccountHolder primaryOwner,
                           String secretKey) {
        super(primaryOwner, secretKey);
        this.minimumBalance = new BigDecimal("0");
        this.interestRate = new BigDecimal("0.2");
        this.creditLimit = new BigDecimal("100");
        setBalance(new Money(this.creditLimit));
    }

    //Constructor for checking accounts with 2 owners, custom creditLimit, custom interestRate
    public CreditCardAccount (AccountHolder primaryOwner, AccountHolder secondaryOwner,
                              String secretKey, BigDecimal interestRate, BigDecimal creditLimit) {
        super(primaryOwner, secondaryOwner, secretKey);
        this.minimumBalance = new BigDecimal("0");
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
        setBalance(new Money(this.creditLimit));
    }

    //Constructor for checking accounts with only 1 owner, custom creditLimit, custom interestRate
    public CreditCardAccount (AccountHolder primaryOwner,
                              String secretKey, BigDecimal interestRate, BigDecimal creditLimit) {
        super(primaryOwner, secretKey);
        this.minimumBalance = new BigDecimal("0");
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
        setBalance(new Money(this.creditLimit));
    }
}
