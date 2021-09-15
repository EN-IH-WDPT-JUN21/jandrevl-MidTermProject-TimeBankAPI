package com.ironhack.TimeBankApiProject.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountHolder extends User{

    @NotNull @NotBlank @NotEmpty @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotNull @NotBlank @NotEmpty
    @ManyToOne
    @JoinColumn(name = "primary_address_id")
    private Address primaryAddress;

    @ManyToOne
    @JoinColumn(name = "mailing_address_id")
    private Address mailingAddress;

    //Constructor for Account Holder with primary address only
    public AccountHolder(String name, String username, String password, Role role,
                         LocalDate dateOfBirth, Address primaryAddress) {
        super(name, username, password, role);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
    }
    // Constructor for Account Holders with both addresses
    public AccountHolder(String name, String username, String password, Role role,
                         LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(name, username, password, role);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }




}
