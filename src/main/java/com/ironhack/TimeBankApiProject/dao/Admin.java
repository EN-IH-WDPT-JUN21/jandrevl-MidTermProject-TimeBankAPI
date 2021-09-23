package com.ironhack.TimeBankApiProject.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Set;

@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Entity
public class Admin extends User{


    //ADMIN constructor
    public Admin(String name, String username, String password, Set<Role> roles) {
        super(name, username, password, roles);
    }
}
