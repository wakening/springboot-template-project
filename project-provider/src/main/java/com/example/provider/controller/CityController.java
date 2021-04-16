package com.example.provider.controller;

import com.alibaba.fastjson.JSON;
import com.example.provider.entity.CityDTO;
import com.example.provider.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation(value = "查询城市列表")
    @GetMapping("/city/list")
    public List<CityDTO> list() {
        List<CityDTO> cityDTOList = cityService.findAll();
        log.info(JSON.toJSONString(cityDTOList));
        return cityDTOList;
    }

}
