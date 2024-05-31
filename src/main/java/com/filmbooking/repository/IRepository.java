package com.filmbooking.repository;

import java.util.List;
import java.util.Map;

public interface IRepository<T> {
    /**
     * Insert Model to database
     * @param t Model
     * @return boolean true if success, false if fail
     */
    boolean insert(T t);

    /**
     * Update Model to database
     * @param t Model
     * @return boolean true if success, false if fail
     */
    boolean update(T t);

    /**
     * Delete Model from database
     * @param t Model
     * @return boolean true if success, false if fail
     */
    boolean delete(T t);

    /**
     * Select Model from database
     * @param id Model ID
     * @return Model
     */
    T select(Object id);

    /**
     * Select all Model from database
     * @return List of Model
     */
    List<T> selectAll();

    /**
     * Select all Model from database with conditions
     * @param conditions is a map of conditions
     * @return List of Model with limit and offset
     */
    List<T> selectAll(Map<String, Object> conditions);

    /**
     * Select all Model from database with limit and offset
     * @param limit limit data to get from database
     * @param offset offset data to get from database
     * @return List of Model with limit and offset
     */
    List<T> selectAll(int limit, int offset);
    /**
     * Select all Model from database with limit, offset, and order
     * @param limit limit data to get from database
     * @param offset offset data to get from database
     * @param order order data to get from database
     * @return List of Model with limit, offset, and order
     */
    List<T> selectAll(int limit, int offset, String order);
    /**
     * Select all Model from database with limit, offset, order, and filters
     * @param limit limit data to get from database
     * @param offset offset data to get from database
     * @param order order data to get from database
     * @param filters filters data to get from database
     * @return List of Model with limit, offset, order, and filters
     */
    List<T> selectAll(int limit, int offset, String order, Map<String, Object> filters);
    /**
     * Count all records
     * @return long count of records
     */
    long countRecords();



}
