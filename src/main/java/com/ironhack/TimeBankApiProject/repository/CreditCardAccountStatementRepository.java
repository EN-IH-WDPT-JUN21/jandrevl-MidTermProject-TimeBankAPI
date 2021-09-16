package com.ironhack.TimeBankApiProject.repository;

import com.ironhack.TimeBankApiProject.dao.CreditCardAccountStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardAccountStatementRepository extends JpaRepository<CreditCardAccountStatement, Long> {
}
