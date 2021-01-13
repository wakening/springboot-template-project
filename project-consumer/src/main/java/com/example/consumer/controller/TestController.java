package com.example.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试服务是否成功启动
 *
 * @author wakening
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/")
    public Long ping() {
        log.info("ping");
        return System.currentTimeMillis();
    }

    @RequestMapping(value = "/save/city/{cityName}", method = RequestMethod.GET)
    public String echo(@PathVariable String cityName) {
        String forObject = restTemplate.getForObject("http://example-project-provider/city/" + cityName, String.class);
        log.info(forObject);
        return forObject;
    }

}
