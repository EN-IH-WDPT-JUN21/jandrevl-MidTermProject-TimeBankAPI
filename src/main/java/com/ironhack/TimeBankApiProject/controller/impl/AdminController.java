package com.ironhack.TimeBankApiProject.controller.impl;

import com.ironhack.TimeBankApiProject.controller.interfaces.IAdminController;
import com.ironhack.TimeBankApiProject.dao.Address;
import com.ironhack.TimeBankApiProject.dao.CheckingAccount;
import com.ironhack.TimeBankApiProject.repository.AddressRepository;
import com.ironhack.TimeBankApiProject.repository.CheckingAccountRepository;
import com.ironhack.TimeBankApiProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController implements IAdminController {

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    @Autowired
    private AddressRepository addressRepository;


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String printHelloTimeBank() {
        return "Dear Administrator,\n\nWelcome to TimeBank!! Use your Power wisely";
    }


    @GetMapping("/accounts/checkingaccounts/balance/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceByAccountNumber(@PathVariable(name = "accountNumber") Long accountNumber) {
        Optional<CheckingAccount> optionalCheckingAccount = checkingAccountRepository.findByAccountNumber(accountNumber);
        return optionalCheckingAccount.isPresent() ? optionalCheckingAccount.get().getBalance() : null;
    }


    @GetMapping("/accounts/checkingaccounts/primaryowner/{primaryOwner}")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckingAccount> getCheckingAccountsByPrimaryOwner(@PathVariable(name = "primaryOwner") Long userId) {
        return checkingAccountRepository.findByPrimaryOwner(userId);
    }


    @GetMapping("/addresses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Address getAddressByAddressId(@PathVariable(name = "id") Long id) {
        Optional<Address> optionalAddress = addressRepository.findByAddressId(id);
        return optionalAddress.isPresent() ? optionalAddress.get() : null;
    }


    @PostMapping("/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody @Valid Address address) {
        return addressRepository.save(address);
    }

}
