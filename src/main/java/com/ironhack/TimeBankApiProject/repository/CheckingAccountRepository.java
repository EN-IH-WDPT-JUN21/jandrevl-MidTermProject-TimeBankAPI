package com.ironhack.TimeBankApiProject.repository;


import com.ironhack.TimeBankApiProject.dao.CheckingAccount;
import com.ironhack.TimeBankApiProject.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {





//    @Query(value = "SELECT * FROM checking_account INNER JOIN user ON checking_account.primary_owner = user.id WHERE checking_account.primary_owner = ':userId'", nativeQuery = true)


}
