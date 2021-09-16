package com.ironhack.TimeBankApiProject.repository;

import com.ironhack.TimeBankApiProject.dao.SavingsAccountStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountStatementRepository extends JpaRepository<SavingsAccountStatement, Long> {
}
