package com.ironhack.TimeBankApiProject.service.impl;

import com.ironhack.TimeBankApiProject.dao.Account;
import com.ironhack.TimeBankApiProject.repository.AccountRepository;
import com.ironhack.TimeBankApiProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account updateBalance(Long accountNumber, Money balance) {
        Optional<Account> storedAccount = accountRepository.findByAccountNumber(accountNumber);
        if(storedAccount.isPresent()) storedAccount.get().setBalance(balance);
        return accountRepository.save(storedAccount.get());

    }
}
