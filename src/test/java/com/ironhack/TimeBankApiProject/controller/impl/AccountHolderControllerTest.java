package com.ironhack.TimeBankApiProject.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.TimeBankApiProject.controller.dto.TransferDto;
import com.ironhack.TimeBankApiProject.repository.RoleRepository;
import com.ironhack.TimeBankApiProject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountHolderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithUserDetails("homer")
    void helloCustomer() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/accountholders")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Welcome to TimeBank!"));

    }

    @Test
    @WithUserDetails("homer")
    void getAccountHolderAccounts() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/accountholders/accounts")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("2012-05-20"));
        assertTrue(result.getResponse().getContentAsString().contains("2015-01-01"));
        assertFalse(result.getResponse().getContentAsString().contains("2013-05-20"));

    }

    @Test
    @WithUserDetails("homer")
    void getAccountBalance_userIsOwnerOfAccount() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/accountholders/accounts/balance/1")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("800"));
    }

//    @Test
//    @WithUserDetails("homer")
//    void getAccountBalance_userIsNOTOwnerOfAccount() throws Exception {
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            MvcResult result = mockMvc.perform(
//                    get("/accountholders/accounts/balance/3")
//            ).andExpect(status().is5xxServerError()).andReturn();
//        });
//
//        assertTrue(exception.getMessage().contains("You are not an Owner"));
//
//
//    }

    @Test
    @WithUserDetails("picklerick")
    void moneyTransfer() throws Exception {

        TransferDto transferDto = new TransferDto(4L, 5L,
                "Stan Smith", new BigDecimal("500"));

        String body = objectMapper.writeValueAsString(transferDto);

        MvcResult mvcResult = mockMvc.perform(
                patch("/accountholders/transfers")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();



        assertTrue(mvcResult.getResponse().getContentAsString().contains("Transfer to Stan Smith"));

    }
}