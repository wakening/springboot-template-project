package com.example.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wakening
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ExampleProjectConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleProjectConsumerApplication.class, args);
    }

}
