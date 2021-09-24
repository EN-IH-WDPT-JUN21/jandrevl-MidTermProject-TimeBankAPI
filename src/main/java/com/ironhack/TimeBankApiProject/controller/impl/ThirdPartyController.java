package com.ironhack.TimeBankApiProject.controller.impl;


import com.ironhack.TimeBankApiProject.controller.dto.ThirdPartyDto;
import com.ironhack.TimeBankApiProject.controller.interfaces.IThirdPartyController;
import com.ironhack.TimeBankApiProject.dao.Role;
import com.ironhack.TimeBankApiProject.dao.ThirdParty;
import com.ironhack.TimeBankApiProject.enums.RoleTypes;
import com.ironhack.TimeBankApiProject.repository.RoleRepository;
import com.ironhack.TimeBankApiProject.repository.ThirdPartyRepository;
import com.ironhack.TimeBankApiProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
public class ThirdPartyController implements IThirdPartyController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/thirdparty")
    @ResponseStatus(HttpStatus.OK)
    public String messageToThirdParties() {
        return "Restricted Area to Third Parties only";
    }

    @PostMapping("/admin/thirdparties")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createNewThirdParty(@RequestBody @Valid ThirdPartyDto thirdPartyDto) {

        if(userRepository.findByUsername(thirdPartyDto.getUsername()).isPresent()){
            throw new IllegalArgumentException("Username already exists.Please choose another");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(3L).get());

        ThirdParty thirdParty = new ThirdParty(thirdPartyDto.getName(), thirdPartyDto.getUsername(),
                thirdPartyDto.getPassword(), thirdPartyDto.getHashedKey(), roles);

        Role role = new Role(RoleTypes.THIRDPARTY, thirdParty);

        thirdPartyRepository.save(thirdParty);

        roleRepository.save(role);

        return thirdParty;
    }


}
