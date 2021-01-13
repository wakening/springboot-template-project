package com.example.consumer.controller.service;

import com.example.consumer.controller.conf.ServiceModule;
import com.example.consumer.controller.service.impl.TestFeignServiceHystrixImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wakening
 */
@FeignClient(name = ServiceModule.EXAMPLE_PROJECT_PROVIDER, fallback = TestFeignServiceHystrixImpl.class)
public interface TestFeignService {

    @GetMapping(value = "/city/{cityName}")
    String save(@PathVariable("cityName") String cityName);

    @GetMapping(value = "/city/list")
    String list();

}
