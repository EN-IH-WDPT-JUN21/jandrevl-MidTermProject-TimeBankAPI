package com.ironhack.TimeBankApiProject.dao;


import com.ironhack.TimeBankApiProject.enums.AccountStatus;
import com.ironhack.TimeBankApiProject.utils.Constants;
import com.ironhack.TimeBankApiProject.utils.Money;
import com.ironhack.TimeBankApiProject.utils.PasswordUtil;
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
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_number")
    protected Long accountNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "primary_owner")
    private User primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner")
    private User secondaryOwner;

    @Embedded
    @AttributeOverride( name = "amount", column = @Column(name = "balance"))
    protected Money balance;

    @Column(name = "penalty_fee")
    private final BigDecimal penaltyFee = Constants.penaltyFee;

    @Column(name = "minimum_balance")
    protected BigDecimal minimumBalance;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "account_status")
    @Enumerated(value = EnumType.STRING)
    private AccountStatus status;


    //Constructor for accounts with 2 Owners
    public Account (User primaryOwner, User secondaryOwner, String secretKey) {
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        setSecretKey(secretKey);
        setStatus(AccountStatus.ACTIVE);
        setCreationDate(LocalDate.now());
        setBalance(new Money(BigDecimal.ZERO));
    }

    //Constructor for accounts with 1 Owner only
    public Account (User primaryOwner, String secretKey) {
        setPrimaryOwner(primaryOwner);
        setSecretKey(secretKey);
        setStatus(AccountStatus.ACTIVE);
        setCreationDate(LocalDate.now());
        setBalance(new Money(BigDecimal.ZERO));
    }



    public void setSecretKey(String secretKey) {
        this.secretKey = PasswordUtil.encryptPassword(secretKey);

    }



}
