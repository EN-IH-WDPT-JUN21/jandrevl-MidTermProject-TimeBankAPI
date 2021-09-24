package com.ironhack.TimeBankApiProject.repository;


import com.ironhack.TimeBankApiProject.dao.Account;
import com.ironhack.TimeBankApiProject.dao.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(Long accountNumber);

    @Query(value = "SELECT a FROM Account a INNER JOIN a.primaryOwner u WHERE a.primaryOwner.id = :userId")
    List<Account> findByPrimaryOwner(Long userId);

    @Query(value = "SELECT a FROM Account a WHERE a.primaryOwner.id = :userId OR a.secondaryOwner.id = :userId")
    List<Account> findByOwner(Long userId);

}
