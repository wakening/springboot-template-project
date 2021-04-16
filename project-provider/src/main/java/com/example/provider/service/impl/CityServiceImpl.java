package com.example.provider.service.impl;

import com.example.provider.dao.CityMapper;
import com.example.provider.entity.CityDTO;
import com.example.provider.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wakening
 */
@Slf4j
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<CityDTO> findAll() {
        return this.cityMapper.findAll();
    }

}
