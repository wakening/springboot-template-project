package com.example.provider.service;

import com.example.repository.entity.City;

import java.util.List;

/**
 * @author wakening
 */
public interface CityService {

    void save(City city);

    List<City> findAll();

}
