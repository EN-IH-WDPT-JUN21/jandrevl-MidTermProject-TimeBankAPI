package com.ironhack.TimeBankApiProject.repository;

import com.ironhack.TimeBankApiProject.dao.Account;
import com.ironhack.TimeBankApiProject.dao.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {

    List<Statement> findByAccountAndMomentOfTransactionAfter(Account account, LocalDateTime start);

    List<Statement> findByMomentOfTransactionAfter(LocalDateTime start);
    
}
