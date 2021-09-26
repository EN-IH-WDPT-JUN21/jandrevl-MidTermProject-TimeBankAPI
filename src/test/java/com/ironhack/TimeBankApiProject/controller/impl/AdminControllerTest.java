package com.ironhack.TimeBankApiProject.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.TimeBankApiProject.controller.dto.AccountDto;
import com.ironhack.TimeBankApiProject.controller.dto.AccountHolderDto;
import com.ironhack.TimeBankApiProject.repository.AccountRepository;
import com.ironhack.TimeBankApiProject.repository.RoleRepository;
import com.ironhack.TimeBankApiProject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

//    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AdminController adminController;


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void printHelloTimeBank() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/admin")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Dear Administrator"));
    }


    @Test
    void getUserById() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/admin/users/4")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Banco Santander"));
    }

    @Test
    void createAccountHolder() throws Exception {

        AccountHolderDto accountHolderDto = new AccountHolderDto("Marge Simpson", "marge",
                "password", LocalDate.of(1969,6,1),
                1L, 1L);

        String body = objectMapper.writeValueAsString(accountHolderDto);
        MvcResult result = mockMvc.perform(
                post("/admin/users/accountholders")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Marge Simpson"));
    }

    @Test
    void createCheckingAccount() throws Exception {

        AccountDto accountDto = new AccountDto(2L,null,"password",
                null,null,null,null);

        String body = objectMapper.writeValueAsString(accountDto);
        MvcResult result = mockMvc.perform(
                post("/admin/accounts/checkingaccounts")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(LocalDate.now().toString()));
    }

    @Test
    void openStudentAccount_Test() {


        AccountDto accountDto = new AccountDto(8L,null,"password",
                null,null,null,null);

        adminController.openStudentAccount(accountDto);

        assertTrue(userRepository.findByUsername("morty").isPresent());

        assertTrue(!accountRepository.findByOwner(userRepository.findByUsername("morty").get().getId()).isEmpty());

    }

    @Test
    void openCheckingAccount() {
        AccountDto accountDto = new AccountDto(9L,null,"password",
                null,null,null,null);

        adminController.openCheckingAccount(accountDto);

        assertEquals(1, accountRepository.findByOwner(userRepository.findByUsername("francine").get().getId()).size());
    }

    @Test
    void createSavingsAccount() {
    }

    @Test
    void openSavingsWithDefaultInterestAndMinimumBalance() {
    }

    @Test
    void openSavingsWithCustomInterestAndMinimumBalance() {
    }

    @Test
    void createCreditCardAccount() {
    }

    @Test
    void updateBalance() {
    }
}