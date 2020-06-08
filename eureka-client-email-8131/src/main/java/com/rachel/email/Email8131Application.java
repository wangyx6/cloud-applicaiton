package com.rachel.email;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.rachel.common.po")
public class Email8131Application {

    public static void main(String[] args) {
        SpringApplication.run(Email8131Application.class, args);
    }
}
