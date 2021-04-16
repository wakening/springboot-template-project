package com.example.provider.service;

import com.example.provider.entity.CityDTO;

import java.util.List;

/**
 * @author wakening
 */
public interface CityService {

    List<CityDTO> findAll();

}
