package com.filmbooking.repository;

import java.util.List;

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

}
