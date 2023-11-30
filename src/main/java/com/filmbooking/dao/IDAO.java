package com.filmbooking.dao;

import com.filmbooking.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public interface IDAO<T> {

    void openSession();
    default void closeSession() {
        getSession().close();
    }
    List<T> getAll();

    T getByID(String id);

    void save(T t);

    void update(T t);

    void delete(T t);

    Session getSession();
}
