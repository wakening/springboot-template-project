package com.example.project.controller;

import com.example.project.entity.City;
import com.example.project.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试mysql读写
 *
 * @author wakening
 */
@RestController
public class CityController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/city/{cityName}")
    public City city(@PathVariable String cityName) {
        City city = new City();
        city.setCityName(cityName);
        demoService.save(city);
        return city;
    }

}
