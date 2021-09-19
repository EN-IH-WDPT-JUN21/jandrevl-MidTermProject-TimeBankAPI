package com.ironhack.TimeBankApiProject.dao;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ironhack.TimeBankApiProject.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "primary_address_id")
    private Address primaryAddress;

    @ManyToOne
    @JoinColumn(name = "mailing_address_id")
    private Address mailingAddress;

    @Column(name = "hashed_key_third_party")
    private String hashedKey;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Role> roles;


    //ADMIN constructor
    public User(String name, String username, String password) {
        setName(name);
        setUsername(username);
        setPassword(password);
//        setRoles(roles);
    }

    //ACCOUNTHOLDER constructor
    public User(String name, String username, String password,
                         LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        setName(name);
        setUsername(username);
        setPassword(password);
//        setRoles(roles);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }

    //THIRDPARTY constructor
    public User (String name, String username, String password, String hashedKey) {
        setName(name);
        setUsername(username);
        setPassword(password);
//        setRoles(roles);
        setHashedKey(hashedKey);
    }

    public void setPassword(String password) {
        this.password = PasswordUtil.encryptPassword(password);
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = PasswordUtil.encryptPassword(hashedKey);
    }





}
