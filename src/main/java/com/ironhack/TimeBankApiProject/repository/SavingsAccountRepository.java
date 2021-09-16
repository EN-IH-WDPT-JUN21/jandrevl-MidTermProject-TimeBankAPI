package com.ironhack.TimeBankApiProject.repository;


import com.ironhack.TimeBankApiProject.dao.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}
