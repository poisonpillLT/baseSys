package com.lt.dao;

import java.util.List;

/**
 * 基础Dao
 * Created by LT on 2017/8/29.
 */
public interface BaseDao<T> {

    int add(T entity);

    int delete(String id);

    int delete(T entity);

    int update(T entity);

    List<T> get(T entity);

    T get(String id);

    class BaseSqlBuilder{

    }

}
