package com.ironhack.TimeBankApiProject.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Admin extends User{



    public Admin (String name, String username, String password, Role role) {
        super(name, username, password, role);

    }
}
