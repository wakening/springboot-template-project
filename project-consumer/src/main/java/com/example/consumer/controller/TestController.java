package com.example.consumer.controller;

import com.example.consumer.controller.conf.ServiceModule;
import com.example.consumer.controller.service.TestFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试服务调用
 *
 * @author wakening
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestFeignService testFeignService;

    @GetMapping(value = "/rest/city/save/{cityName}")
    public String testRestTemplate(@PathVariable String cityName) {
        String result = restTemplate.getForObject("http://" + ServiceModule.EXAMPLE_PROJECT_PROVIDER + "/city/save/" + cityName, String.class);
        log.info(result);
        return result;
    }

    @GetMapping(value = "/feign/city/save/{cityName}")
    public String testFeign(@PathVariable String cityName) {
        String result = testFeignService.save(cityName);
        log.info(result);
        return result;
    }

    @GetMapping(value = "/feign/city/list")
    public String testFeign() {
        String result = testFeignService.list();
        log.info(result);
        return result;
    }

}
