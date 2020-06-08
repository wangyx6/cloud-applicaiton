package com.rachel.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EntityScan("com.rachel.common.po")
public class User8111Application {

    public static void main(String[] args) {
        SpringApplication.run(User8111Application.class, args);
    }
}
