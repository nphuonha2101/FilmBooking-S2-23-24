package com.filmbooking.dao;

import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IDAO<T> {
    void setSessionProvider(HibernateSessionProvider sessionProvider);


    long getTotalRecords();

    List<T> getByOffset(int offset, int limit);

    List<T> getAll();

    T getByID(String id, boolean isLongID);

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);
}
