package com.ironhack.TimeBankApiProject;

import com.ironhack.TimeBankApiProject.dao.Role;
import com.ironhack.TimeBankApiProject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimeBankApiProjectApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(TimeBankApiProjectApplication.class, args);
	}


	@Override
	public void run(String... args) {

		Role accountHolder = new Role()


	}
}
