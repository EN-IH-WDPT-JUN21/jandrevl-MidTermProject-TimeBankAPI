package com.ironhack.TimeBankApiProject.repository;


import com.ironhack.TimeBankApiProject.dao.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {


}
