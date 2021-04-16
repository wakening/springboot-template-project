package com.example.provider;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wakening
 */
//@PropertySource(value = "classpath:nacos/DEFAULT_GROUP/example-project-provider-dev.yaml", ignoreResourceNotFound = true, factory = YamlPropertySourceFactory.class)
@MapperScan(value = "com.example.provider.dao")
@EnableScheduling
@EnableEncryptableProperties
@EnableAdminServer
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ExampleProjectProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleProjectProviderApplication.class, args);
    }

}
