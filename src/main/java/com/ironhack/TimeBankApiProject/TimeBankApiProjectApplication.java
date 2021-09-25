package com.ironhack.TimeBankApiProject;

import com.ironhack.TimeBankApiProject.repository.*;
import com.ironhack.TimeBankApiProject.utils.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

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
	AccountRepository accountRepository;





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

	}

}
