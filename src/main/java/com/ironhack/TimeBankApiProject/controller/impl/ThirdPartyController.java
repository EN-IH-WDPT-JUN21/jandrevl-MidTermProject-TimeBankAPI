package com.ironhack.TimeBankApiProject.controller.impl;


import com.ironhack.TimeBankApiProject.controller.dto.ThirdPartyDto;
import com.ironhack.TimeBankApiProject.controller.dto.ThirdPartyTransactionDto;
import com.ironhack.TimeBankApiProject.controller.interfaces.IThirdPartyController;
import com.ironhack.TimeBankApiProject.dao.*;
import com.ironhack.TimeBankApiProject.enums.AccountStatus;
import com.ironhack.TimeBankApiProject.enums.RoleTypes;
import com.ironhack.TimeBankApiProject.repository.*;
import com.ironhack.TimeBankApiProject.utils.Constants;
import com.ironhack.TimeBankApiProject.utils.Money;
import com.ironhack.TimeBankApiProject.utils.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RestController
public class ThirdPartyController implements IThirdPartyController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StatementRepository statementRepository;
    @Autowired
    private SecurityContextUtils securityContextUtils;



    @GetMapping("/thirdparty")
    @ResponseStatus(HttpStatus.OK)
    public String messageToThirdParties() {
        return "Restricted Area to Third Parties only";
    }

    @PostMapping("/admin/thirdparties")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createNewThirdParty(@RequestBody @Valid ThirdPartyDto thirdPartyDto) {

        if(userRepository.findByUsername(thirdPartyDto.getUsername()).isPresent()){
            throw new IllegalArgumentException("Username already exists.Please choose another");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(3L).get());

        ThirdParty thirdParty = new ThirdParty(thirdPartyDto.getName(), thirdPartyDto.getUsername(),
                thirdPartyDto.getPassword(), thirdPartyDto.getHashedKey(), roles);

        Role role = new Role(RoleTypes.THIRDPARTY, thirdParty);

        thirdPartyRepository.save(thirdParty);

        roleRepository.save(role);

        return thirdParty;
    }

    @PatchMapping("/thirdparty/transactions")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String thirdPartyTransaction(@RequestBody @Valid ThirdPartyTransactionDto thirdPartyTransactionDto) {

        Account account = accountRepository.findByAccountNumber(thirdPartyTransactionDto.getAccount()).get();

        User thirdParty = userRepository.findById(securityContextUtils.getAuthenticatedUserId()).get();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(passwordEncoder.matches(account.getSecretKey(), thirdPartyTransactionDto.getAccountSecretKey())) {
            throw new IllegalArgumentException("Secret Keys do not match");
        }

        BigDecimal finalBalance = account.getBalance().getAmount().add(thirdPartyTransactionDto.getAmount());

        //if the account is frozen and the transaction is a debit transaction, the transaction is not possible
        if (account.getStatus() == AccountStatus.FROZEN &&
                thirdPartyTransactionDto.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Transaction not possible");
        }

        if(finalBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Insufficient funds");
        }

        account.setBalance(new Money(finalBalance));

        String transactionDescription = "Transaction by " + thirdParty.getName();

        Statement transaction = new Statement(account, transactionDescription, thirdPartyTransactionDto.getAmount(),
                account.getBalance());

        accountRepository.save(account);
        statementRepository.save(transaction);

        //Charges penalty fee only if initial balance is above minimum and final balance is below
        BigDecimal initialBalance = finalBalance.add(thirdPartyTransactionDto.getAmount());

        if (initialBalance.compareTo(account.getMinimumBalance()) > 0) {
            chargePenaltyFeeIfBalanceBelowMinimum(account, finalBalance);
        }

        return transactionDescription + " of " + new Money(thirdPartyTransactionDto.getAmount()).toString();

    }

    public void chargePenaltyFeeIfBalanceBelowMinimum(Account account, BigDecimal finalBalance) {
        if (finalBalance.compareTo(account.getMinimumBalance()) < 0) {

            account.setBalance(new Money(finalBalance.subtract(Constants.penaltyFee)));

            Statement penaltyDebit = new Statement(account, "Penalty fee - Balance below minimum",
                    Constants.penaltyFee.negate(), account.getBalance());

            accountRepository.save(account);
            statementRepository.save(penaltyDebit);

        }
    }


}
