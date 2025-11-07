package com.example.kok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KokApplication {

    public static void main(String[] args) {
        SpringApplication.run(KokApplication.class, args);
    }

}
