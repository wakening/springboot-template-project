package com.example.project.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DemoMapper extends BaseMapper<Map<String, Object>, Long> {

}
