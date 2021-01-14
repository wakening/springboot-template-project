package com.example.provider;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wakening
 */
@EnableAdminServer
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ExampleProjectProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleProjectProviderApplication.class, args);
    }

}
