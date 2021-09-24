package com.ironhack.TimeBankApiProject.repository;

import com.ironhack.TimeBankApiProject.dao.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
}
