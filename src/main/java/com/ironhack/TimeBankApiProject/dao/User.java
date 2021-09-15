package com.ironhack.TimeBankApiProject.dao;


import com.ironhack.TimeBankApiProject.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Role role;

    public User(String username, String password, Role role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public void setPassword(String password) {
        this.password = PasswordUtil.encryptPassword(password);
    }





}
