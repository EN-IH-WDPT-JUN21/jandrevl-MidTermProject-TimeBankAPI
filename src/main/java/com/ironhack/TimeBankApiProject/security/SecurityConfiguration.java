package com.ironhack.TimeBankApiProject.security;


import com.ironhack.TimeBankApiProject.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.httpBasic();
        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/").authenticated()
                .mvcMatchers(HttpMethod.GET, "/accountholders/**").hasRole("ACCOUNTHOLDER")
                .mvcMatchers(HttpMethod.GET, "/thirdparty/**").hasAnyRole("THIRDPARTY")
                .mvcMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/admin/**").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll();
    }

}
