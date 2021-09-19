package com.ironhack.TimeBankApiProject.repository;

import com.ironhack.TimeBankApiProject.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByAddressId(Long id);
}
