package com.ironhack.TimeBankApiProject.controller.impl;

import com.ironhack.TimeBankApiProject.controller.interfaces.IAdminController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController implements IAdminController {

    @GetMapping("/hello-world")
    @ResponseStatus(HttpStatus.OK)
    public String printHelloTimeBank() {
        return "Dear Customer,\n\nWelcome to TimeBank!!";
    }

}
