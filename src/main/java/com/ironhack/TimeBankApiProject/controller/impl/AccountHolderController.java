package com.ironhack.TimeBankApiProject.controller.impl;


import com.ironhack.TimeBankApiProject.controller.dto.TransferDto;
import com.ironhack.TimeBankApiProject.controller.interfaces.IAccountHolderController;
import com.ironhack.TimeBankApiProject.dao.Account;
import com.ironhack.TimeBankApiProject.dao.AccountHolder;
import com.ironhack.TimeBankApiProject.dao.Statement;
import com.ironhack.TimeBankApiProject.dao.User;
import com.ironhack.TimeBankApiProject.enums.AccountStatus;
import com.ironhack.TimeBankApiProject.repository.AccountRepository;
import com.ironhack.TimeBankApiProject.repository.StatementRepository;
import com.ironhack.TimeBankApiProject.repository.UserRepository;
import com.ironhack.TimeBankApiProject.utils.Constants;
import com.ironhack.TimeBankApiProject.utils.FraudDetection;
import com.ironhack.TimeBankApiProject.utils.Money;
import com.ironhack.TimeBankApiProject.utils.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accountholders")
public class AccountHolderController implements IAccountHolderController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StatementRepository statementRepository;
    @Autowired
    private SecurityContextUtils securityContextUtils;
    @Autowired
    private FraudDetection fraudDetection;


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String helloCustomer() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return "Dear Customer " + username + "\n\nWelcome to TimeBank!";
    }


    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountHolderAccounts() {

        Long userId = securityContextUtils.getAuthenticatedUserId();

        return accountRepository.findByOwner(userId);
    }


    @GetMapping("/accounts/balance/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Money getAccountBalance(@PathVariable("accountNumber") Long accountNumber) {

        Long userId = securityContextUtils.getAuthenticatedUserId();

        User accountHolder = userRepository.findById(userId).get();

//        List<Account> userAccounts = accountRepository.findByOwner(userId);

        Account account = accountRepository.findByAccountNumber(accountNumber).get();

        if(accountHolder != account.getPrimaryOwner() && accountHolder != account.getSecondaryOwner()) {
            throw new IllegalArgumentException("You are not an Owner of this account");
        }

        return account.getBalance();

    }


    @PatchMapping("/transfers")
    @ResponseStatus(HttpStatus.CREATED)
    public Statement moneyTransfer(@RequestBody @Valid TransferDto transferDto) {

        Long userId = securityContextUtils.getAuthenticatedUserId();
        User authenticatedUser = userRepository.findById(userId).get();

        Account originAccount = accountRepository.findByAccountNumber(transferDto.getOriginAccountNumber()).get();

        if (originAccount.getStatus() == AccountStatus.FROZEN) {
            throw new IllegalStateException("Transaction not possible");
        }

        if(authenticatedUser != originAccount.getPrimaryOwner() &&
                authenticatedUser != originAccount.getSecondaryOwner()) {
            throw new IllegalArgumentException("You are not an Owner of the Origin Account");
        }

        Account beneficiaryAccount =
                accountRepository.findByAccountNumber(transferDto.getBeneficiaryAccountNumber()).get();

        if (transferDto.getAmount().compareTo(originAccount.getBalance().getAmount()) > 0) {
            throw new IllegalArgumentException("Not enough funds for this transfer");
        }

        Money newOriginBalance = new Money(originAccount.getBalance().decreaseAmount(transferDto.getAmount()));
        Money newBeneficiaryBalance = new Money(beneficiaryAccount.getBalance().increaseAmount(transferDto.getAmount()));

        originAccount.setBalance(newOriginBalance);
        beneficiaryAccount.setBalance(newBeneficiaryBalance);

        accountRepository.save(originAccount);
        accountRepository.save(beneficiaryAccount);

        Statement debit = new Statement(originAccount, "Transfer to " + transferDto.getBeneficiaryName(),
                transferDto.getAmount().negate(), newOriginBalance);
        Statement credit = new Statement(beneficiaryAccount,
                "Transfer from " + originAccount.getPrimaryOwner().getName(),
                transferDto.getAmount(), newBeneficiaryBalance);

        statementRepository.save(debit);
        statementRepository.save(credit);

        Money oldOriginAccountBalance = new Money(newOriginBalance.getAmount().add(transferDto.getAmount()));

        //Charge penalty fee if balance below minimum, but avoids double charge if balance already below minimum
        if (newOriginBalance.getAmount().compareTo(originAccount.getMinimumBalance()) < 0 &&
                oldOriginAccountBalance.getAmount().compareTo(originAccount.getMinimumBalance()) > 0) {

            originAccount.setBalance(new Money(newOriginBalance.decreaseAmount(Constants.penaltyFee)));

            Statement penaltyDebit = new Statement(originAccount, "Penalty fee - Balance below minimum",
                    Constants.penaltyFee.negate(), originAccount.getBalance());

            accountRepository.save(originAccount);
            statementRepository.save(penaltyDebit);

        }

        fraudDetection.fraudDetectionRoutine(originAccount);
        fraudDetection.fraudDetectionRoutine(beneficiaryAccount);

        return debit;

    }

}
