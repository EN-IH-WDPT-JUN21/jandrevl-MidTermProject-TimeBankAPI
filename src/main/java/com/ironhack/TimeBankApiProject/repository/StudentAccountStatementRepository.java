package com.ironhack.TimeBankApiProject.repository;


import com.ironhack.TimeBankApiProject.dao.StudentAccountStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAccountStatementRepository extends JpaRepository<StudentAccountStatement, Long> {

}
