package com.mohit.esd_mini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EsdMiniApplication {

    public static void main(String[] args) {
            SpringApplication.run(EsdMiniApplication.class, args);
    }

}
