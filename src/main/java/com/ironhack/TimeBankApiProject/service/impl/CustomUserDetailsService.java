package com.ironhack.TimeBankApiProject.service.impl;

import com.ironhack.TimeBankApiProject.dao.User;
import com.ironhack.TimeBankApiProject.repository.UserRepository;
import com.ironhack.TimeBankApiProject.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User does not exist");
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(user.get());

        return customUserDetails;
    }
}
