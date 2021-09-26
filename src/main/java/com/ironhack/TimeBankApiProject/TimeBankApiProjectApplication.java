package com.ironhack.TimeBankApiProject;

import com.ironhack.TimeBankApiProject.dao.CheckingAccount;
import com.ironhack.TimeBankApiProject.dao.SavingsAccount;
import com.ironhack.TimeBankApiProject.repository.*;
import com.ironhack.TimeBankApiProject.utils.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class TimeBankApiProjectApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CheckingAccountRepository checkingAccountRepository;
	@Autowired
	SavingsAccountRepository savingsAccountRepository;
	@Autowired
	CreditCardAccountRepository creditCardAccountRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	ScheduledTasks scheduledTasks;





	public static void main(String[] args) {
		SpringApplication.run(TimeBankApiProjectApplication.class, args);
	}


	@Override
	public void run(String... args) {

//		User admin1 = userRepository.findById(1L).get();
//		String hashedPassword = PasswordUtil.encryptPassword("password");
//		admin1.setPassword(hashedPassword);
//		userRepository.save(admin1);

//		User thirdPartyUser = new User("Banco Santander", "santander", "password",
//				"password");
//
//		userRepository.save(thirdPartyUser);
//
//		Role thirdParty = new Role (RoleTypes.THIRDPARTY, userRepository.findByUsername("santander").get());
//		roleRepository.save(thirdParty);

//		CheckingAccount checkingAccount1 = new CheckingAccount(userRepository.getById(2L), "password");
//		BigDecimal balance = new BigDecimal("5500000");
//		Money newBalance = new Money(balance);
//		checkingAccount1.setBalance(newBalance);
//		checkingAccountRepository.save(checkingAccount1);

//		System.out.println(savingsAccountRepository.findById(12L).get().getInterestRate());


//		accountRepository.findById(3L).get().setBalance(new Money(new BigDecimal("200")));


//		ScheduledTasks t = new ScheduledTasks();
//		t.repeatedPrint();

//		LocalDate testDate = LocalDate.now().minusYears(1);
//		List<CheckingAccount> accounts = checkingAccountRepository.findByDateOfLastMaintenanceFeeBefore(testDate);
//
//		for(CheckingAccount account : accounts) {
//			System.out.println(account.getDateOfLastMaintenanceFee());
//		}

//		scheduledTasks.chargeInterest(creditCardAccountRepository.findByAccountNumber(3L).get());

//		SavingsAccount account = savingsAccountRepository.findById(4L).get();
//		BigDecimal interestRate = account.getInterestRate();
//		BigDecimal savingsBalance = account.getBalance().getAmount();
//		BigDecimal yearlyInterest = savingsBalance.multiply(interestRate).setScale(2, RoundingMode.HALF_EVEN);
//		System.out.println(yearlyInterest);

//		List<SavingsAccount> accounts = savingsAccountRepository.findByDateOfLastInterestBefore(LocalDate.now().minusYears(1));
//		scheduledTasks.yearlySavingsInterestCredit(accounts);


	}

}
