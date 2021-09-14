package com.ironhack.TimeBankApiProject.dao;


import com.ironhack.TimeBankApiProject.utils.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
public class CheckingAccount extends Account {

    private final BigDecimal monthlyMaintenanceFee = new BigDecimal("12");

//private LocalDate dateOfLastMaintenanceFee; ver o artigo sobre @Scheduled que o Salva me enviou

    public CheckingAccount (AccountHolder primaryOwner, AccountHolder secondaryOwner,
                            String secretKey) {
        super(primaryOwner, secondaryOwner, secretKey);
        this.minimumBalance = new BigDecimal("250");
    }


}
