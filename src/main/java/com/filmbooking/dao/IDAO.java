package com.filmbooking.dao;

import java.util.List;

public interface IDAO<T> {

    List<T> getAll();

    T getByID(String id);

    void save(T t);

    void update(T t);

    void delete(T t);

}
