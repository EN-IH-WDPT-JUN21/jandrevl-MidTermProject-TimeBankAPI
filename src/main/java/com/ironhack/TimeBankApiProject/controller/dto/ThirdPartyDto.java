package com.ironhack.TimeBankApiProject.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyDto {

    private String name;
    private String username;
    private String password;
    private String hashedKey;
}
