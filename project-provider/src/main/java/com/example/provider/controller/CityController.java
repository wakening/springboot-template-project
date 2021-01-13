package com.example.provider.controller;

import com.alibaba.fastjson.JSON;
import com.example.repository.entity.City;
import com.example.provider.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试mysql读写
 *
 * @author wakening
 */
@Slf4j
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/city/{cityName}")
    public City insert(@PathVariable String cityName) {
        City city = new City();
        city.setCityName(cityName);
        cityService.save(city);
        log.info(JSON.toJSONString(city));
        return city;
    }

    @RequestMapping("/city/list")
    public List<City> list() {
        List<City> cityList = cityService.findAll();
        log.info(JSON.toJSONString(cityList));
        return cityList;
    }

}
