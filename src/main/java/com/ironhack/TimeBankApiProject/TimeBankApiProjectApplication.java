package com.ironhack.TimeBankApiProject;

import com.ironhack.TimeBankApiProject.dao.User;
import com.ironhack.TimeBankApiProject.repository.RoleRepository;
import com.ironhack.TimeBankApiProject.repository.UserRepository;
import com.ironhack.TimeBankApiProject.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimeBankApiProjectApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;





	public static void main(String[] args) {
		SpringApplication.run(TimeBankApiProjectApplication.class, args);
	}


	@Override
	public void run(String... args) {

//		User admin1 = userRepository.findById(1L).get();
//		String hashedPassword = PasswordUtil.encryptPassword("password");
//		admin1.setPassword(hashedPassword);
//		userRepository.save(admin1);








	}
}
