package com.rachel.code;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.rachel.common.po")
public class Code8121Application {

    public static void main(String[] args) {
        SpringApplication.run(Code8121Application.class, args);
    }
}
