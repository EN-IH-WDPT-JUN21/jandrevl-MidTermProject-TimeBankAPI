package com.ironhack.TimeBankApiProject.dao;


import com.ironhack.TimeBankApiProject.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ThirdParty extends User{

    @Column(name = "hashed_key_third_party")
    private String hashedKey;

    //THIRDPARTY constructor
    public ThirdParty(String name, String username, String password, String hashedKey, Set<Role> roles) {
        super(name, username, password, roles);
        setHashedKey(hashedKey);
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = PasswordUtil.encryptPassword(hashedKey);
    }

}
