package com.ironhack.TimeBankApiProject.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolderDto {

    private String name;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private Long primaryAddressId;
    private Long mailingAddressId;
    private Long roleId;
}
