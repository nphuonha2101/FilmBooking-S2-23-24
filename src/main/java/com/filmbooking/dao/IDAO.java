package com.filmbooking.dao;

import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IDAO<T> {
    void setSessionProvider(HibernateSessionProvider sessionProvider);

    List<T> getAll();

    T getByID(String id);

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);
}
