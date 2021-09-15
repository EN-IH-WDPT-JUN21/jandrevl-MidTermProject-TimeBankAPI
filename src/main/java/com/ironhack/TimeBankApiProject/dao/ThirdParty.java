package com.ironhack.TimeBankApiProject.dao;


import com.ironhack.TimeBankApiProject.utils.PasswordUtil;
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
public class ThirdParty extends User{

    @Column(name = "hashed_key_third_party")
    private String hashedKey;


    public ThirdParty (String name, String username, String password, Role role, String hashedKey) {
        super(name, username, password, role);
        setHashedKey(hashedKey);
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = PasswordUtil.encryptPassword(hashedKey);
    }


}
