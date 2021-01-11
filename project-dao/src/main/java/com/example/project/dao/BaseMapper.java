package com.example.project.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 抽象DAO层基类 提供一些简便方法
 * 泛型 ： M 表示实体类型；ID表示主键类型
 *
 * @author wakening
 */
public interface BaseMapper<M, ID extends Serializable> {
    void insert(M m);

    void insertSelective(M m);

    void updateByPrimaryKey(M m);

    void updateByPrimaryKeySelective(M m);

    void deleteByPrimaryKey(ID id);

    void delete(M m);

    void deleteByIds(ID[] ids);

    M selectByPrimaryKey(ID id);

    long exists(ID id);

    List<M> findAll();
}
