package com.ironhack.TimeBankApiProject.dao;

import com.ironhack.TimeBankApiProject.utils.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentAccountStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account")
    private StudentAccount account;

    private LocalDateTime momentOfTransaction;

    @Embedded
    private Money amount;


}
