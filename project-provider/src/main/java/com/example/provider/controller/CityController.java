package com.example.provider.controller;

import com.alibaba.fastjson.JSON;
import com.example.repository.entity.City;
import com.example.provider.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试mysql读写
 *
 * @author wakening
 */
@Slf4j
@RestController
public class CityController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/city/{cityName}")
    public City city(@PathVariable String cityName) {
        City city = new City();
        city.setCityName(cityName);
        demoService.save(city);
        log.info(JSON.toJSONString(city));
        return city;
    }

}
