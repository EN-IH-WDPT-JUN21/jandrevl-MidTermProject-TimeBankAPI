package com.ironhack.TimeBankApiProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.TimeBankApiProject.dao.Role;
import com.ironhack.TimeBankApiProject.dao.User;
import com.ironhack.TimeBankApiProject.enums.RoleTypes;
import com.ironhack.TimeBankApiProject.repository.RoleRepository;
import com.ironhack.TimeBankApiProject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getBalanceByAccountNumber() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/admin/accounts/balances/1")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("800"));

    }

    @Test
    void getAccountsByPrimaryOwner() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/admin/accounts/accountholders/primaryowners/2")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("2010-01-01"));
        assertTrue(result.getResponse().getContentAsString().contains("2012-05-20"));
        assertFalse(result.getResponse().getContentAsString().contains("2013-05-20"));


    }

    @Test
    void getAccountsByOwner() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/admin/accounts/accountholders/2")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("2012-05-20"));
        assertTrue(result.getResponse().getContentAsString().contains("2015-01-01"));
        assertFalse(result.getResponse().getContentAsString().contains("2013-05-20"));
    }
}