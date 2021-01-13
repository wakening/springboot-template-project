package com.example.provider.service.impl;

import com.example.repository.dao.CityMapper;
import com.example.repository.entity.City;
import com.example.provider.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wakening
 */
@Service
public class DemoServiceImpl implements DemoService {

    private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;

    @Override
    public void save(City city) {
        this.cityMapper.insert(city);
    }

}
