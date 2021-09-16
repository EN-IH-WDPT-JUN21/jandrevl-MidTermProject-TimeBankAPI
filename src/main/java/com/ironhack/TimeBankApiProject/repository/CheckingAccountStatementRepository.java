package com.ironhack.TimeBankApiProject.repository;

import com.ironhack.TimeBankApiProject.dao.CheckingAccountStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountStatementRepository extends JpaRepository<CheckingAccountStatement, Long> {
}
