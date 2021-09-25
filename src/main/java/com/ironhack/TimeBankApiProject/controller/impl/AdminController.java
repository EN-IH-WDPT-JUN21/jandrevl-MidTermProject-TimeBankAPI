package com.ironhack.TimeBankApiProject.controller.impl;

import com.ironhack.TimeBankApiProject.controller.dto.AccountDto;
import com.ironhack.TimeBankApiProject.controller.dto.AccountHolderDto;
import com.ironhack.TimeBankApiProject.controller.dto.BalanceDto;
import com.ironhack.TimeBankApiProject.controller.interfaces.IAdminController;
import com.ironhack.TimeBankApiProject.dao.*;
import com.ironhack.TimeBankApiProject.enums.RoleTypes;
import com.ironhack.TimeBankApiProject.repository.*;
import com.ironhack.TimeBankApiProject.service.impl.AccountService;
import com.ironhack.TimeBankApiProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
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
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StudentAccountRepository studentAccountRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
    private CreditCardAccountRepository creditCardAccountRepository;
    @Autowired
    private AccountService accountService;



    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String printHelloTimeBank() {
        return "Dear Administrator,\n\nWelcome to TimeBank!! Use your Power wisely";
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

        roles.add(roleRepository.findById(2L).get());

        String name = accountHolderDto.getName();

        String username = accountHolderDto.getUsername();
        if(userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        LocalDate dateOfBirth = accountHolderDto.getDateOfBirth();

        String password = accountHolderDto.getPassword();

        AccountHolder accountHolder = new AccountHolder(name, username, password, dateOfBirth, primaryAddress,
                mailingAddress, roles);

        Role role = new Role(RoleTypes.ACCOUNTHOLDER, accountHolder);
        accountHolderRepository.save(accountHolder);
        roleRepository.save(role);

        return accountHolder;

    }


    @PostMapping("/accounts/checkingaccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody @Valid AccountDto accountDto) {

        AccountHolder primaryOwner = accountHolderRepository.findById(accountDto.getPrimaryOwnerId()).get();

        if (primaryOwner.getDateOfBirth().plusYears(24).isAfter(LocalDate.now())) {
            return openStudentAccount(accountDto);
        } else {
            return openCheckingAccount(accountDto);
        }

    }

    public StudentAccount openStudentAccount(AccountDto accountDto) {


        User primaryOwner = userRepository.findById(accountDto.getPrimaryOwnerId()).get();

        User secondaryOwner = getSecondaryOwnerIfNotNull(accountDto);

        String secretKey = accountDto.getSecretKey();

        StudentAccount studentAccount = new StudentAccount(primaryOwner, secondaryOwner, secretKey);

        return studentAccountRepository.save(studentAccount);
    }


    public CheckingAccount openCheckingAccount(AccountDto accountDto) {

        User primaryOwner = userRepository.findById(accountDto.getPrimaryOwnerId()).get();

        User secondaryOwner = getSecondaryOwnerIfNotNull(accountDto);

        String secretKey = accountDto.getSecretKey();

        CheckingAccount checkingAccount = new CheckingAccount(primaryOwner, secondaryOwner, secretKey);

        return checkingAccountRepository.save(checkingAccount);

    }

    @PostMapping("/accounts/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount createSavingsAccount(@RequestBody @Valid AccountDto accountDto) {

        if(accountDto.getMinimumBalance() == null && accountDto.getSavingsInterestRate() == null) {

            return openSavingsWithDefaultInterestAndMinimumBalance(accountDto);

        } else return  openSavingsWithCustomInterestAndMinimumBalance(accountDto);

    }


    public SavingsAccount openSavingsWithDefaultInterestAndMinimumBalance(AccountDto accountDto) {

        User primaryOwner = userRepository.findById(accountDto.getPrimaryOwnerId()).get();

        User secondaryOwner = getSecondaryOwnerIfNotNull(accountDto);

        String secretKey = accountDto.getSecretKey();

        return savingsAccountRepository.save(new SavingsAccount(primaryOwner, secondaryOwner, secretKey));

    }


    public SavingsAccount openSavingsWithCustomInterestAndMinimumBalance(AccountDto accountDto) {

        User primaryOwner = userRepository.findById(accountDto.getPrimaryOwnerId()).get();

        User secondaryOwner = getSecondaryOwnerIfNotNull(accountDto);

        String secretKey = accountDto.getSecretKey();

        BigDecimal minimumBalance = accountDto.getMinimumBalance();

        BigDecimal interestRate = accountDto.getSavingsInterestRate();

        return savingsAccountRepository.save(new SavingsAccount(primaryOwner, secondaryOwner,
                secretKey, minimumBalance, interestRate));
    }


    @PostMapping("/accounts/creditcardaccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCardAccount createCreditCardAccount(@RequestBody @Valid AccountDto accountDto) {

        User primaryOwner = userRepository.findById(accountDto.getPrimaryOwnerId()).get();

        User secondaryOwner = getSecondaryOwnerIfNotNull(accountDto);

        String secretKey = accountDto.getSecretKey();

        BigDecimal interestRate = accountDto.getCreditCardInterestRate();

        BigDecimal creditLimit = accountDto.getCreditLimit();

        if (interestRate == null && creditLimit == null) {
            CreditCardAccount creditCardAccount = new CreditCardAccount(primaryOwner,
                    secondaryOwner, secretKey);
            return creditCardAccountRepository.save(creditCardAccount);
        } else {
            CreditCardAccount creditCardAccount = new CreditCardAccount(primaryOwner,
                    secondaryOwner, secretKey, interestRate, creditLimit);
            return creditCardAccountRepository.save(creditCardAccount);
        }
    }


    private User getSecondaryOwnerIfNotNull(AccountDto accountDto) {
        User secondaryOwner;
        if (accountDto.getSecondaryOwnerId() != null) {
            secondaryOwner = userRepository.findById(accountDto.getSecondaryOwnerId()).get();
        } else secondaryOwner = null;
        return secondaryOwner;
    }


    @PatchMapping("/accounts/balances/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateBalance(@PathVariable("accountNumber") Long accountNumber,
                                 @RequestBody @Valid BalanceDto balanceDto) {
        Money moneyBalance = new Money(balanceDto.getBalance());
        return accountService.updateBalance(accountNumber, moneyBalance);

    }



}
