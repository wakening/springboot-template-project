package com.example.repository.dao;

import com.example.repository.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wakening
 */
@Mapper
@Repository
public interface CityMapper extends BaseMapper<City, Long> {

    @Override
    @Select("select id, city_name from city_info")
    List<City> findAll();

}
