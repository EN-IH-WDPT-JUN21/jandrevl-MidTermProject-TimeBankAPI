package com.ironhack.TimeBankApiProject.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    public static String encryptPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(15);
        return passwordEncoder.encode(password);
    }
}
