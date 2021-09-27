package com.ironhack.TimeBankApiProject.dao;

import com.ironhack.TimeBankApiProject.utils.Constants;
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
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavingsAccount extends Account{

    @Column(name = "savings_interest_rate", columnDefinition = "DECIMAL(5,5)")
    @Positive
    private BigDecimal interestRate;

    @Column(name = "date_of_last_paid_interest")
    private LocalDate dateOfLastInterest;

    //Constructor for checking accounts with 2 owners, default minimum Balance, default interestRate
    public SavingsAccount (User primaryOwner, User secondaryOwner,
                            String secretKey) {
        super(primaryOwner, secondaryOwner, secretKey);
        setMinimumBalance(Constants.defaultSavingsMinimumBalance);
        setInterestRate(Constants.defaultSavingsInterest);
        setDateOfLastInterest(LocalDate.now());
    }


    //Constructor for checking accounts with 2 owners and custom interest and minimumBalance
    public SavingsAccount (User primaryOwner, User secondaryOwner,
                           String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        setDateOfLastInterest(LocalDate.now());

    }

}
