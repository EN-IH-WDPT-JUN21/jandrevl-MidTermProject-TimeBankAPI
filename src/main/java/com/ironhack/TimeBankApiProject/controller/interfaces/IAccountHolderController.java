package com.ironhack.TimeBankApiProject.controller.interfaces;

import com.ironhack.TimeBankApiProject.controller.dto.TransferDto;
import com.ironhack.TimeBankApiProject.dao.Account;
import com.ironhack.TimeBankApiProject.dao.Statement;
import com.ironhack.TimeBankApiProject.utils.Money;

import java.util.List;

public interface IAccountHolderController {

    String helloCustomer();

    List<Account> getAccountHolderAccounts();

    Money getAccountBalance(Long accountNumber);

    Statement moneyTransfer (TransferDto transferDto);
}
