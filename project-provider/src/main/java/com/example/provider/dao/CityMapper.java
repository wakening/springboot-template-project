package com.example.provider.dao;

import com.example.provider.entity.CityDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wakening
 */
@Mapper
@Repository
public interface CityMapper {

    @Select("SELECT 1 AS id, 'beijing' AS city_name")
    List<CityDTO> findAll();

}
