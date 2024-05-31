package com.filmbooking.services;

import java.util.List;
import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public interface IService<T> {

    /**
     * Get record by slug
     *
     * @param slug is the slug of record
     * @return T instance
     *         <br>
     *         <b>If object has not slug, you must override this method</b>
     */
    T getBySlug(String slug);

    /**
     * Get record by ID
     *
     * @param id is the ID of record
     * @return T instance
     */
    T select(Object id);

    /**
     * Get total record rows
     *
     * @return long total record rows
     */
    long countRecords();

    /**
     * Save a record
     *
     * @param t is the record to save
     * @return boolean true if success, false if fail
     *         <br>
     * <b>If object cannot save, you must override this method</b>
     */
    boolean insert(T t);

    /**
     * Update a record
     *
     * @param t is the record to update
     * @return boolean true if success, false if fail
     *         <br>
     *         <b>If object cannot update, you must override this method</b>
     */
    boolean update(T t);

    /**
     * Delete a record
     *
     * @param t is the record to delete
     * @return boolean true if success, false if fail
     *         <br>
     *         <b>If object cannot delete, you must override this method</b>
     */
    boolean delete(T t);

    /**
     * Select all records without limit and offset
     * @return List of T
     */
    List<T> selectAll();
    /**
     * Select all records with limit and offset
     * @param limit limit data to get from database
     * @param offset offset data to get from database
     * @return List of T with limit and offset
     */
    List<T> selectAll(int limit, int offset);

    /**
     * Select all records with filters
     * @param filters a Map of columns in database with its value
     * @return List of T with filters
     */
    List<T> selectAll(Map<String, Object> filters);

    /**
     * Select all records with limit, offset, and order
     * @param limit limit data to get from database
     * @param offset offset data to get from database
     * @param order order data to get from database. Ex: "id DESC"
     * @return List of T with limit, offset, and order
     */
    List<T> selectAll(int limit, int offset, String order);
    /**
     * Select all records with limit, offset, order, and filters
     * @param limit limit data to get from database
     * @param offset offset data to get from database
     * @param order order data to get from database. Ex: "id DESC"
     * @param filters filters data to get from database. Ex: {"name": "nphuo", "age": 20}
     * @return List of T with limit, offset, order, and filters
     */
    List<T> selectAll(int limit, int offset, String order, Map<String, Object> filters);
}
