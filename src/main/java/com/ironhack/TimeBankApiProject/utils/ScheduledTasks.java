package com.ironhack.TimeBankApiProject.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@EnableScheduling
@SpringBootApplication
public class ScheduledTasks {

    @Scheduled(cron = "0/4 * * * * * ")
    public void repeatedPrint(){
        System.out.println(LocalDateTime.now());
    }

}
