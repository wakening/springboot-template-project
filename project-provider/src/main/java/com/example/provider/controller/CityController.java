package com.example.provider.controller;

import com.alibaba.fastjson.JSON;
import com.example.provider.service.CityService;
import com.example.repository.entity.City;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试mysql读写
 *
 * @author wakening
 */
@Api("测试城市信息读写mysql")
@Slf4j
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "保存一条城市信息")
    @ApiImplicitParam(name = "cityName", value = "城市名称", required = true, dataType = "String")
    @GetMapping("/city/save/{cityName}")
    public City insert(@PathVariable String cityName) {
        City city = new City();
        city.setCityName(cityName);
        cityService.save(city);
        log.info(JSON.toJSONString(city));
        return city;
    }

    @ApiOperation(value = "查询城市列表")
    @GetMapping("/city/list")
    public List<City> list() {
        List<City> cityList = cityService.findAll();
        log.info(JSON.toJSONString(cityList));
        return cityList;
    }

}
