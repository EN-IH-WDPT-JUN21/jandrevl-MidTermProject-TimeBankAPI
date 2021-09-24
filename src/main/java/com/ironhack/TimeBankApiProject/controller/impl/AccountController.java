package com.ironhack.TimeBankApiProject.controller.impl;

import com.ironhack.TimeBankApiProject.controller.interfaces.ICheckingAccountController;
import com.ironhack.TimeBankApiProject.dao.Account;
import com.ironhack.TimeBankApiProject.dao.CheckingAccount;
import com.ironhack.TimeBankApiProject.repository.AccountRepository;
import com.ironhack.TimeBankApiProject.repository.CheckingAccountRepository;
import com.ironhack.TimeBankApiProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController implements ICheckingAccountController {

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("admin/accounts/balance/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceByAccountNumber(@PathVariable(name = "accountNumber") Long accountNumber) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        return optionalAccount.isPresent() ? optionalAccount.get().getBalance() : null;
    }


    @GetMapping("admin/accounts/accountholders/primaryowners/{primaryOwner}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountsByPrimaryOwner(@PathVariable(name = "primaryOwner") Long userId) {
        return accountRepository.findByPrimaryOwner(userId);
    }


    @GetMapping("admin/accounts/accountholders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountsByOwner(@PathVariable("id") Long id) {
        return accountRepository.findByOwner(id);
    }



}
