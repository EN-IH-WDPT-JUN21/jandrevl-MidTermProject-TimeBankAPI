package com.ironhack.TimeBankApiProject.utils;


import com.ironhack.TimeBankApiProject.dao.Account;
import com.ironhack.TimeBankApiProject.dao.Statement;
import com.ironhack.TimeBankApiProject.enums.AccountStatus;
import com.ironhack.TimeBankApiProject.repository.AccountRepository;
import com.ironhack.TimeBankApiProject.repository.StatementRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class FraudDetection {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StatementRepository statementRepository;

    private BigDecimal totalAmountEver = BigDecimal.ZERO;


    public void fraudDetectionRoutine(Account susAccount) {

        updateTotalAmountEver();

        if(areThereMoreThan2TransactionsInLastSecond(susAccount) ||
                totalOf24HoursOfSusAccount(susAccount).compareTo(getTotalAmountEver()) >0) {

            susAccount.setStatus(AccountStatus.FROZEN);
        }

        accountRepository.save(susAccount);
    }



    public boolean areThereMoreThan2TransactionsInLastSecond (Account susAccount) {

        List<Statement> susTransactions = statementRepository.findByAccountAndMomentOfTransactionAfter(susAccount,
                LocalDateTime.now().minusSeconds(1));

        return (susTransactions.size() > 2);

    }


    public BigDecimal totalOf24HoursOfSusAccount(Account susAccount) {

        List<Statement> susTransactions = statementRepository.findByAccountAndMomentOfTransactionAfter(susAccount,
                LocalDateTime.now().minusHours(24));

        BigDecimal totalAmount = BigDecimal.ZERO;

        for(Statement transaction : susTransactions) {

            totalAmount = totalAmount.add(transaction.getAmount().abs());

        }

        return totalAmount;
    }


    public void updateTotalAmountEver() {

        List<Statement> all24HourTransactions =
                statementRepository.findByMomentOfTransactionAfter(LocalDateTime.now().minusHours(24));

        BigDecimal totalAmount = BigDecimal.ZERO;

        for(Statement transaction : all24HourTransactions) {

            totalAmount = totalAmount.add(transaction.getAmount().abs());
        }

        if(totalAmount.compareTo(getTotalAmountEver()) > 0) {
            setTotalAmountEver(totalAmount);
        }

    }




}
