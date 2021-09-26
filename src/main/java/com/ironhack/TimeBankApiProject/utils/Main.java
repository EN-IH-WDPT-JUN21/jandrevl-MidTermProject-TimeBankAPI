package com.ironhack.TimeBankApiProject.utils;

import com.ironhack.TimeBankApiProject.dao.SavingsAccount;
import com.ironhack.TimeBankApiProject.dao.User;
import com.ironhack.TimeBankApiProject.repository.SavingsAccountRepository;
import com.ironhack.TimeBankApiProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class Main {



    public static void main(String[] args) {

//        Money money = new Money(new BigDecimal("5500000"));
//        System.out.println(money.toString());

        System.out.println(PasswordUtil.encryptPassword("password"));

        System.out.println(LocalDateTime.now());

        BigDecimal annualInterest = new BigDecimal("0.2");
        BigDecimal monthlyInterest = annualInterest.divide(new BigDecimal("12"), 4, RoundingMode.HALF_EVEN);
        System.out.println(monthlyInterest);







    }


}
