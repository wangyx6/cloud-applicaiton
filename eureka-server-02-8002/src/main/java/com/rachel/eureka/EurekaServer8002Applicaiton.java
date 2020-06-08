package com.rachel.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer8002Applicaiton {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer8002Applicaiton.class, args);
    }
}
