package com.ironhack.TimeBankApiProject.controller.impl;

import com.ironhack.TimeBankApiProject.controller.dto.AccountDto;
import com.ironhack.TimeBankApiProject.controller.dto.AccountHolderDto;
import com.ironhack.TimeBankApiProject.controller.interfaces.IAdminController;
import com.ironhack.TimeBankApiProject.dao.*;
import com.ironhack.TimeBankApiProject.enums.RoleTypes;
import com.ironhack.TimeBankApiProject.repository.*;
import com.ironhack.TimeBankApiProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController implements IAdminController {

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StudentAccountRepository studentAccountRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;


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


    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable(name = "id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.isPresent()? optionalUser.get() : null;
    }


    @PostMapping("/users/accountholders")
    @ResponseStatus(HttpStatus.CREATED)
    public User createAccountHolder(@RequestBody @Valid AccountHolderDto accountHolderDto) {

        Address primaryAddress = addressRepository.findByAddressId(accountHolderDto.getPrimaryAddressId()).get();

        Address mailingAddress = new Address();
        if(accountHolderDto.getMailingAddressId() == null) {
            mailingAddress = null; }
        else mailingAddress = addressRepository.findByAddressId(accountHolderDto.getMailingAddressId()).get();

        Set<Role> roles = new HashSet<>();

        roles.add(roleRepository.findById(accountHolderDto.getRoleId()).get());

        String name = accountHolderDto.getName();

        String username = accountHolderDto.getUsername();

        LocalDate dateOfBirth = accountHolderDto.getDateOfBirth();

        String password = accountHolderDto.getPassword();

        User user = new User(name, username, password, dateOfBirth, primaryAddress,
                mailingAddress, roles);

        Role role = new Role(RoleTypes.ACCOUNTHOLDER, user);
        userRepository.save(user);
        roleRepository.save(role);

        return user;

    }


    @PostMapping("/accounts/checkingaccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody @Valid AccountDto accountDto) {

        User primaryOwner = userRepository.findById(accountDto.getPrimaryOwnerId()).get();

        if (primaryOwner.getDateOfBirth().plusYears(24).isAfter(LocalDate.now())) {
            return openStudentAccount(accountDto);
        } else {
            return openCheckingAccount(accountDto);
        }

    }

    public StudentAccount openStudentAccount(AccountDto accountDto) {


        User primaryOwner = userRepository.findById(accountDto.getPrimaryOwnerId()).get();

        User secondaryOwner;
        if(accountDto.getSecondaryOwnerId() != null) {
            secondaryOwner = userRepository.findById(accountDto.getSecondaryOwnerId()).get();
        } else secondaryOwner = null;

        String secretKey = accountDto.getSecretKey();

        StudentAccount studentAccount = new StudentAccount(primaryOwner, secondaryOwner, secretKey);

        return studentAccountRepository.save(studentAccount);
    }


    public CheckingAccount openCheckingAccount(AccountDto accountDto) {

        User primaryOwner = userRepository.findById(accountDto.getPrimaryOwnerId()).get();

        User secondaryOwner;
        if(accountDto.getSecondaryOwnerId() != null) {
            secondaryOwner = userRepository.findById(accountDto.getSecondaryOwnerId()).get();
        } else secondaryOwner = null;

        String secretKey = accountDto.getSecretKey();

        CheckingAccount checkingAccount = new CheckingAccount(primaryOwner, secondaryOwner, secretKey);

        return checkingAccountRepository.save(checkingAccount);

    }

    @PostMapping("/accounts/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount createSavingsAccount(@RequestBody @Valid AccountDto accountDto) {

        if(accountDto.getMinimumBalance() == null && accountDto.getInterestRate() == null) {

            return openSavingsWithDefaultInterestAndMinimumBalance(accountDto);

        } else return  openSavingsWithCustomInterestAndMinimumBalance(accountDto);

    }


    public SavingsAccount openSavingsWithDefaultInterestAndMinimumBalance(AccountDto accountDto) {

        User primaryOwner = userRepository.findById(accountDto.getPrimaryOwnerId()).get();

        User secondaryOwner;
        if(accountDto.getSecondaryOwnerId() != null) {
            secondaryOwner = userRepository.findById(accountDto.getSecondaryOwnerId()).get();
        } else secondaryOwner = null;

        String secretKey = accountDto.getSecretKey();

        return savingsAccountRepository.save(new SavingsAccount(primaryOwner, secondaryOwner, secretKey));

    }


    public SavingsAccount openSavingsWithCustomInterestAndMinimumBalance(AccountDto accountDto) {

        User primaryOwner = userRepository.findById(accountDto.getPrimaryOwnerId()).get();

        User secondaryOwner;
        if(accountDto.getSecondaryOwnerId() != null) {
            secondaryOwner = userRepository.findById(accountDto.getSecondaryOwnerId()).get();
        } else secondaryOwner = null;

        String secretKey = accountDto.getSecretKey();

        BigDecimal minimumBalance = accountDto.getMinimumBalance();

        BigDecimal interestRate = accountDto.getInterestRate();

        return savingsAccountRepository.save(new SavingsAccount(primaryOwner, secondaryOwner,
                secretKey, minimumBalance, interestRate));
    }


//    @PostMapping("/accounts/creditcardaccounts")
//    @ResponseStatus(HttpStatus.CREATED)
//    public


}
