package com.ironhack.TimeBankApiProject.dao;


import com.ironhack.TimeBankApiProject.utils.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @Column(name = "moment_of_transaction")
    private LocalDateTime momentOfTransaction;

    private String description;

    @Column(name = "transaction_amount")
    private BigDecimal amount;

    @Embedded
    @Column(name = "account_balance")
    private Money remainingAccountBalance;

    public Statement(Account account, String description, BigDecimal amount, Money remainingAccountBalance) {
        setAccount(account);
        setDescription(description);
        setAmount(amount);
        this.momentOfTransaction = LocalDateTime.now();
        setRemainingAccountBalance(remainingAccountBalance);
    }


}
