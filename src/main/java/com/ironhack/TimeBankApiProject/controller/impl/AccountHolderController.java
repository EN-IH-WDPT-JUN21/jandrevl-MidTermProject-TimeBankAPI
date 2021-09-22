package com.ironhack.TimeBankApiProject.controller.impl;


import com.ironhack.TimeBankApiProject.controller.interfaces.IAccountHolderController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountholders")
public class AccountHolderController implements IAccountHolderController {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String helloCustomer() {
        return "Dear Customer\n\nWelcome to TimeBank!";
    }
}
