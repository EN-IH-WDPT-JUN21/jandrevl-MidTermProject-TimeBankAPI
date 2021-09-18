package com.ironhack.TimeBankApiProject.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class StudentAccount extends Account{

    public StudentAccount(User primaryOwner, String secretKey) {
        super(primaryOwner, secretKey);
        this.minimumBalance = BigDecimal.ZERO;
    }

    public StudentAccount(User primaryOwner, User secondaryOwner, String secretKey) {
        super(primaryOwner, secondaryOwner, secretKey);
        this.minimumBalance = BigDecimal.ZERO;
    }
}
