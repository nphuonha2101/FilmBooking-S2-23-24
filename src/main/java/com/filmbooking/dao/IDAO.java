/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.dao;

import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IDAO<T> {
    /**
     * Set the session provider for the DAO
     * <br>
     * {@link HibernateSessionProvider} is provided a {@link org.hibernate.Session} to the DAO
     * <br>
     * {@link org.hibernate.Session} is used to access the database
     * @param sessionProvider the session provider
     */
    void setSessionProvider(HibernateSessionProvider sessionProvider);

    /**
     * Get the total number of records in the database
     * @return the total number of records in the database
     */
    long getTotalRecordRows();

    /**
     * Get all records from the database without any condition
     * @return {@link IDAO<T>} for chaining and for decorating
     */
    IDAO<T> getAll();

    /**
     * Get a record by its ID
     * @param id the ID of the record
     * @param isLongID whether the ID is a long or String id
     */
    T getByID(String id, boolean isLongID);

    /**
     * Save an object to the database
     * @param t an object to save
     * @return whether the object is saved successfully
     */
    boolean save(T t);

    /**
     * Update an object in the database
     * @param t an object to update
     * @return whether the object is updated successfully
     */
    boolean update(T t);

    /**
     * Delete an object from the database
     * @param t an object to delete
     * @return whether the object is deleted successfully
     */
    boolean delete(T t);

    /**
     * Get the results from the database
     * <br><br>
     * If the result is not found, it returns an empty list
     * @return the results from the database
     */
    List<T> getMultipleResults();

    /**
     * Get single result from the database
     * <be><br>
     *  If the result is not unique, it will throw an {@link jakarta.persistence.NonUniqueResultException}
     *  <br><br>
     *  If the result is not found, it will throw an {@link jakarta.persistence.NoResultException}
     * @return the single result from the database
     */
    T getSingleResult();

}
