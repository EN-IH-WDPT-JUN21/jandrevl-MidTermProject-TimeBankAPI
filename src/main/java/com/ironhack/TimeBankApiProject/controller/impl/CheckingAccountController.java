package com.ironhack.TimeBankApiProject.controller.impl;

import com.ironhack.TimeBankApiProject.controller.interfaces.ICheckingAccountController;
import com.ironhack.TimeBankApiProject.dao.CheckingAccount;
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
public class CheckingAccountController implements ICheckingAccountController {

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;


    @GetMapping("admin/accounts/checkingaccounts/balance/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceByAccountNumber(@PathVariable(name = "accountNumber") Long accountNumber) {
        Optional<CheckingAccount> optionalCheckingAccount = checkingAccountRepository.findByAccountNumber(accountNumber);
        return optionalCheckingAccount.isPresent() ? optionalCheckingAccount.get().getBalance() : null;
    }


    @GetMapping("admin/accounts/checkingaccounts/primaryowner/{primaryOwner}")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckingAccount> getCheckingAccountsByPrimaryOwner(@PathVariable(name = "primaryOwner") Long userId) {
        return checkingAccountRepository.findByPrimaryOwner(userId);


    }



}
