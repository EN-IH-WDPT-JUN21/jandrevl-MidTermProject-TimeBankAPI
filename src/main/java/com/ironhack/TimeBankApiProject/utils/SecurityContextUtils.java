package com.ironhack.TimeBankApiProject.utils;

import com.ironhack.TimeBankApiProject.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class SecurityContextUtils {

    @Autowired
    private UserRepository userRepository;


    public Long getAuthenticatedUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Long userId = userRepository.findByUsername(username).get().getId();
        return userId;
    }
}
