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

    @NotNull @NotBlank @NotEmpty
    private String name;
    @NotNull @NotBlank @NotEmpty @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @NotNull @NotBlank @NotEmpty
    @ManyToOne
    @JoinColumn(name = "primary_address_id")
    private Address primaryAddress;
    @ManyToOne
    @JoinColumn(name = "mailing_address_id")
    private Address mailingAddress;




}
