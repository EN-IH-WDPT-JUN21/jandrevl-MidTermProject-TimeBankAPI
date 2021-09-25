package com.ironhack.TimeBankApiProject.repository;

import com.ironhack.TimeBankApiProject.dao.CheckingAccount;
import com.ironhack.TimeBankApiProject.dao.CreditCardAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardAccountRepository extends JpaRepository<CreditCardAccount, Long> {

    List<CreditCardAccount> findByDateOfLastInterestBefore(LocalDate date);

    Optional<CreditCardAccount> findByAccountNumber(Long accountNumber);
}
