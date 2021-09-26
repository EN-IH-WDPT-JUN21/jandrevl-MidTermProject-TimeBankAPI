package com.ironhack.TimeBankApiProject.controller.interfaces;

import com.ironhack.TimeBankApiProject.dao.Account;
import com.ironhack.TimeBankApiProject.utils.Money;

import java.util.List;

public interface IAccountController {

    Money getBalanceByAccountNumber(Long accountNumber);

    List<Account> getAccountsByPrimaryOwner(Long userId);

    List<Account> getAccountsByOwner(Long id);
}
