package com.example.provider.service.impl;

import com.example.repository.dao.CityMapper;
import com.example.repository.entity.City;
import com.example.provider.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wakening
 */
@Service
public class CityServiceImpl implements CityService {

    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;

    @Override
    public void save(City city) {
        this.cityMapper.insert(city);
    }

    @Override
    public List<City> findAll() {
        return this.cityMapper.findAll();
    }

}
