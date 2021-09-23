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
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Column(name = "role")
    @JsonManagedReference
    private Set<Role> roles;



    public User(String name, String username, String password, Set<Role> roles) {
        setName(name);
        setUsername(username);
        setPassword(password);
        setRoles(roles);
    }





    public void setPassword(String password) {
        this.password = PasswordUtil.encryptPassword(password);
    }






}
