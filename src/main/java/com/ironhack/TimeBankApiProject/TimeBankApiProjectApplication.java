package com.ironhack.TimeBankApiProject;

import com.ironhack.TimeBankApiProject.dao.Account;
import com.ironhack.TimeBankApiProject.dao.CheckingAccount;
import com.ironhack.TimeBankApiProject.dao.SavingsAccount;
import com.ironhack.TimeBankApiProject.dao.Statement;
import com.ironhack.TimeBankApiProject.repository.*;
import com.ironhack.TimeBankApiProject.utils.FraudDetection;
import com.ironhack.TimeBankApiProject.utils.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	@Autowired
	StatementRepository statementRepository;
	@Autowired
	FraudDetection fraudDetection;






	public static void main(String[] args) {
		SpringApplication.run(TimeBankApiProjectApplication.class, args);
	}


	@Override
	public void run(String... args) {





	}

}
