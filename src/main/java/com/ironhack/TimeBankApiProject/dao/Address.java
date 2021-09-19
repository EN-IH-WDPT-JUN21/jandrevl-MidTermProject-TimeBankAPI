package com.ironhack.TimeBankApiProject.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @NotEmpty
    @NotBlank
    private String streetAndNumber;

//    @NotEmpty
//    @NotBlank
    @NotNull
    @Column(name = "zip_code")
    @Digits(integer = 4, fraction = 0, message = "Invalid zip code")
    private Long zipCode;

    @NotEmpty
    @NotBlank
    private String city;

    @NotEmpty
    @NotBlank
    private String country;

    public Address (String streetAndNumber, Long zipCode, String city, String country) {
        setStreetAndNumber(streetAndNumber);
        setZipCode(zipCode);
        setCity(city);
        setCountry(country);
    }

}
