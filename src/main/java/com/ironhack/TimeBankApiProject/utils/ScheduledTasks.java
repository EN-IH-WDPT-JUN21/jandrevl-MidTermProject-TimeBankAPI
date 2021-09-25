package com.ironhack.TimeBankApiProject.utils;

import com.ironhack.TimeBankApiProject.dao.CheckingAccount;
import com.ironhack.TimeBankApiProject.dao.CreditCardAccount;
import com.ironhack.TimeBankApiProject.dao.Statement;
import com.ironhack.TimeBankApiProject.repository.CheckingAccountRepository;
import com.ironhack.TimeBankApiProject.repository.CreditCardAccountRepository;
import com.ironhack.TimeBankApiProject.repository.StatementRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@EnableScheduling
@SpringBootApplication
public class ScheduledTasks {

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    @Autowired
    private StatementRepository statementRepository;
    @Autowired
    private CreditCardAccountRepository creditCardAccountRepository;

    //Teste
//    @Scheduled(cron = "0/4 * * * * * ")
//    public void repeatedPrint(){
//        System.out.println(LocalDateTime.now());
//    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void dailyAccountRoutines() {

        LocalDate oneMonthAgoToday = LocalDate.now().minusMonths(1);
        LocalDate oneYearAgoToday = LocalDate.now().minusYears(1);

        List<CheckingAccount> checkingAccounts = checkingAccountRepository
                .findByDateOfLastMaintenanceFeeBefore(oneMonthAgoToday);

        List<CreditCardAccount> creditCardAccounts = creditCardAccountRepository
                .findByDateOfLastInterestBefore(oneMonthAgoToday);

        if (!checkingAccounts.isEmpty()) {
            monthlyMaintenanceFeeCharge(checkingAccounts);
        }

        if(!creditCardAccounts.isEmpty()) {
            monthlyCreditCardInterestCharge(creditCardAccounts);
        }

    }





    public void monthlyMaintenanceFeeCharge(List<CheckingAccount> accounts) {

        for(CheckingAccount account : accounts) {
                chargeFee(account);
        }
    }

    private void chargeFee(CheckingAccount account) {
        account.setBalance(new Money(account.getBalance().decreaseAmount(Constants.monthlyMaintenanceFee)));
        account.setDateOfLastMaintenanceFee(LocalDate.now());

        Statement maintenanceCharge = new Statement(account, "Monthly Maintenance Fee",
                Constants.monthlyMaintenanceFee.negate(), account.getBalance());

        checkingAccountRepository.save(account);
        statementRepository.save(maintenanceCharge);
    }



    public void monthlyCreditCardInterestCharge(List<CreditCardAccount> accounts) {

        for(CreditCardAccount account: accounts) {
            chargeInterest(account);
        }
    }

    public void chargeInterest(CreditCardAccount account) {

        BigDecimal monthlyInterest = account.getInterestRate().divide(new BigDecimal("12"), 4, RoundingMode.HALF_EVEN);

        BigDecimal interestAmount = account.getCreditLimit().multiply(monthlyInterest);

        account.setBalance(new Money(account.getBalance().decreaseAmount(interestAmount)));
        account.setDateOfLastInterest(LocalDate.now());

        Statement debitInterest = new Statement(account, "Monthly Interest Rate Charge",
                interestAmount.negate(), account.getBalance());

        creditCardAccountRepository.save(account);
        statementRepository.save(debitInterest);
    }

//    public void yearlySavingsInterestCredit(List<>)


}
