package com.ironhack.TimeBankApiProject.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin extends User{

    @Column(name = "admin_name")
    private String name;

    public Admin (String username, String password, Role role, String name) {
        super(username, password, role);
        setName(name);
    }
}
