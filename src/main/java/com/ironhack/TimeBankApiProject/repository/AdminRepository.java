package com.ironhack.TimeBankApiProject.repository;

import com.ironhack.TimeBankApiProject.dao.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
