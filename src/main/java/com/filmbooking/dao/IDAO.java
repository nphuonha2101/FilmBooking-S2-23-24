package com.filmbooking.dao;

import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IDAO<T> {
    void setSessionProvider(HibernateSessionProvider sessionProvider);

    List<T> getAll();

    T getByID(String id);

    void save(T t);

    void update(T t);

    void delete(T t);
}
