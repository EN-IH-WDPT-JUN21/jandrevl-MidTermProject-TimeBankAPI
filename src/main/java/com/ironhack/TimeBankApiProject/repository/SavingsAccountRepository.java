package com.ironhack.TimeBankApiProject.repository;


import com.ironhack.TimeBankApiProject.dao.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    List<SavingsAccount> findByDateOfLastInterestBefore(LocalDate date);
}
