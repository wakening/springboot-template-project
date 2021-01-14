package com.example.consumer.controller.service.impl;

import com.example.consumer.controller.service.TestFeignService;
import org.springframework.stereotype.Component;

/**
 * @author wakening
 */
@Component
public class TestFeignServiceHystrixImpl implements TestFeignService {

    @Override
    public String save(String cityName) {
        return "请求异常";
    }

    @Override
    public String list() {
        return "请求异常";
    }
}
