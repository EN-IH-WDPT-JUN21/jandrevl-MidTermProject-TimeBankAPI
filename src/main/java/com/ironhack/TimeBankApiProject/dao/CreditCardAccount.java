package com.ironhack.TimeBankApiProject.dao;

import com.ironhack.TimeBankApiProject.utils.Constants;
import com.ironhack.TimeBankApiProject.utils.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCardAccount extends Account{

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    //Constructor for checking accounts with 2 owners, default creditLimit, default interestRate
    public CreditCardAccount (User primaryOwner, User secondaryOwner,
                           String secretKey) {
        super(primaryOwner, secondaryOwner, secretKey);
        setMinimumBalance(new BigDecimal("0"));
        setInterestRate(Constants.defaultCreditCardInterest);
        setCreditLimit(Constants.defaultCreditCardLimit);
        setBalance(new Money(this.creditLimit));
    }

    //Constructor for checking accounts with only 1 owner, default creditLimit, default interestRate
    public CreditCardAccount (User primaryOwner,
                           String secretKey) {
        super(primaryOwner, secretKey);
        setMinimumBalance(new BigDecimal("0"));
        setInterestRate(Constants.defaultCreditCardInterest);
        setCreditLimit(Constants.defaultCreditCardLimit);
        setBalance(new Money(this.creditLimit));
    }

    //Constructor for checking accounts with 2 owners, custom creditLimit, custom interestRate
    public CreditCardAccount (User primaryOwner, User secondaryOwner,
                              String secretKey, BigDecimal interestRate, BigDecimal creditLimit) {
        super(primaryOwner, secondaryOwner, secretKey);
        setMinimumBalance(new BigDecimal("0"));
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
        setBalance(new Money(this.creditLimit));
    }

    //Constructor for checking accounts with only 1 owner, custom creditLimit, custom interestRate
    public CreditCardAccount (User primaryOwner,
                              String secretKey, BigDecimal interestRate, BigDecimal creditLimit) {
        super(primaryOwner, secretKey);
        setMinimumBalance(new BigDecimal("0"));
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
        setBalance(new Money(this.creditLimit));
    }

    @Override
    public void setBalance(Money balance) {
        if (balance.getAmount().compareTo(this.creditLimit) > 0) {
            throw new IllegalArgumentException("Credit Limit exceeded");
        }
        this.balance = balance;
    }
}
