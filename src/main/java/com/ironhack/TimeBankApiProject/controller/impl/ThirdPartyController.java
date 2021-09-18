package com.ironhack.TimeBankApiProject.controller.impl;


import com.ironhack.TimeBankApiProject.controller.interfaces.IThirdPartyController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdPartyController implements IThirdPartyController {

    @GetMapping("/thirdparty")
    @ResponseStatus(HttpStatus.OK)
    public String messageToThirdParties() {
        return "Restricted Area to Third Parties only";
    }
}
