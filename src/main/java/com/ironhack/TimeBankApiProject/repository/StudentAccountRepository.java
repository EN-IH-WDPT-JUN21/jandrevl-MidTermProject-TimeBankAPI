package com.ironhack.TimeBankApiProject.repository;


import com.ironhack.TimeBankApiProject.dao.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAccountRepository extends JpaRepository<StudentAccount, Long> {


}
