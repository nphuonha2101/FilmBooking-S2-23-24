package com.filmbooking.services;

import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;
import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public interface IServices<T> {
    /**
     * Set session provider for DAO
     *
     * @param sessionProvider session provider provides {@link org.hibernate.Session} for DAO
     *                        <br>
     * <b>You can only use this method after creating a new instance {@link com.filmbooking.dao.IDAO} in constructor</b>
     */
    void setSessionProvider(HibernateSessionProvider sessionProvider);

    /**
     * Get all records
     *
     * @return IServices<T> instance for chaining or reuse
     * @see IServices#getMultipleResults()
     */
    IServices<T> getAll();

    /**
     * Get records by offset and limit
     *
     * @param offset is the number of records to skip (start from 0)
     * @param limit  is the number of records to get
     * @return IServices<T> instance for chaining or reuse
     * @see IServices#getMultipleResults()
     */
    IServices<T> getByOffset(int offset, int limit);

    /**
     * Get records by conditions
     *
     * @param conditions is a map storing conditions. For example: Map.of("name_=", "Phuong Nha") or more entry in Map;
     * @return IServices<T> instance for chaining or reuse
     * @see IServices#getMultipleResults()
     */
    IServices<T> getByPredicates(Map<String, Object> conditions);

    /**
     * Get record by slug
     *
     * @param slug is the slug of record
     * @return T instance
     * <br>
     * <b>If object has not slug, you must override this method</b>
     */
    T getBySlug(String slug);

    /**
     * Get record by ID
     *
     * @param id is the ID of record
     * @return T instance
     * <br>
     * <b>You must override this method for each class because of long ID and String ID</b>
     */
    T getByID(String id);

    /**
     * Get total record rows
     *
     * @return long total record rows
     */
    long getTotalRecordRows();

    /**
     * Save a record
     *
     * @param t is the record to save
     * @return boolean true if success, false if fail
     * <br>
     * <b>If object cannot save, you must override this method</b>
     */
    boolean save(T t);

    /**
     * Update a record
     *
     * @param t is the record to update
     * @return boolean true if success, false if fail
     * <br>
     * <b>If object cannot update, you must override this method</b>
     */
    boolean update(T t);

    /**
     * Delete a record
     *
     * @param t is the record to delete
     * @return boolean true if success, false if fail
     * <br>
     * <b>If object cannot delete, you must override this method</b>
     */
    boolean delete(T t);

    /**
     * Get multiple results
     *
     * @return List<T> multiple results
     */
    List<T> getMultipleResults();

}
