package com.example.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wakening
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ExampleProjectProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleProjectProviderApplication.class, args);
    }

}
