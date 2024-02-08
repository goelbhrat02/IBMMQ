package com.bluespire.citizensmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class MqSpringBootApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(MqSpringBootApplication.class, args);
        System.out.println("spring boot started....");
    }
}
