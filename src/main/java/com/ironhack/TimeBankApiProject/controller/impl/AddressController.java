package com.ironhack.TimeBankApiProject.controller.impl;

import com.ironhack.TimeBankApiProject.controller.interfaces.IAdressController;
import com.ironhack.TimeBankApiProject.dao.Address;
import com.ironhack.TimeBankApiProject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AddressController implements IAdressController {

    @Autowired
    private AddressRepository addressRepository;


    @PostMapping("admin/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody @Valid Address address) {
        return addressRepository.save(address);
    }


    @GetMapping("admin/addresses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Address getAddressByAddressId(@PathVariable(name = "id") Long id) {
        Optional<Address> optionalAddress = addressRepository.findByAddressId(id);
        return optionalAddress.isPresent() ? optionalAddress.get() : null;
    }

}
