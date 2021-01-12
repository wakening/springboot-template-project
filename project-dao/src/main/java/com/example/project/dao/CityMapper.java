package com.example.project.dao;

import com.example.project.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author wakening
 */
@Repository
@Mapper
public interface CityMapper extends BaseMapper<City, Long> {

}
