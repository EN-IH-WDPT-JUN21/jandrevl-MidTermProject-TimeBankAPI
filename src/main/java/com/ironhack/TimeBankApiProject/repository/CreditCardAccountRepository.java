package com.ironhack.TimeBankApiProject.repository;

import com.ironhack.TimeBankApiProject.dao.CreditCardAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardAccountRepository extends JpaRepository<CreditCardAccount, Long> {
}
