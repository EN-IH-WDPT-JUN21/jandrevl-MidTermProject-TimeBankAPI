package com.ironhack.TimeBankApiProject.dao;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ironhack.TimeBankApiProject.enums.RoleTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(value = EnumType.STRING)
    private RoleTypes name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Role(RoleTypes name, User user) {
        setName(name);
        setUser(user);
    }


}
