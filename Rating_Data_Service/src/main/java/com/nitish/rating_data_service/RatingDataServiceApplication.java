package com.nitish.rating_data_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "resource")
@EnableDiscoveryClient
public class RatingDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatingDataServiceApplication.class, args);
    }

}
