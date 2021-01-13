package com.example.repository.dao;

import com.example.repository.entity.City;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wakening
 */
@Mapper
public interface CityMapper extends BaseMapper<City, Long> {

}
