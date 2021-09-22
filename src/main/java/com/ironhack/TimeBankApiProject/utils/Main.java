package com.ironhack.TimeBankApiProject.utils;

import com.ironhack.TimeBankApiProject.dao.User;
import com.ironhack.TimeBankApiProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


public class Main {



    public static void main(String[] args) {

//        Money money = new Money(new BigDecimal("5500000"));
//        System.out.println(money.toString());

        System.out.println(PasswordUtil.encryptPassword("password"));



    }


}
