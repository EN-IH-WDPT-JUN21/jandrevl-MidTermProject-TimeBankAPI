package com.ironhack.TimeBankApiProject.dao;


import com.ironhack.TimeBankApiProject.enums.AccountStatus;
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
@MappedSuperclass
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;

//    @Column(name = "primary_owner")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "primary_owner")
    private AccountHolder primaryOwner;

//    @Column(name = "secondary_owner")
    @ManyToOne
    @JoinColumn(name = "secondary_owner")
    private AccountHolder secondaryOwner;

    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "penalty_fee")
    private final BigDecimal penaltyFee = new BigDecimal("40");

    @Column(name = "minimum_balance")
    protected BigDecimal minimumBalance;

    @Column(name = "creation_date")
    private LocalDate creationDate = LocalDate.now();

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "account_status")
    @Enumerated(value = EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;



    public Account (AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        setSecretKey(secretKey);

    }

    public void setSecretKey(String secretKey) {
        this.secretKey = PasswordUtil.encryptPassword(secretKey);

    }



}
