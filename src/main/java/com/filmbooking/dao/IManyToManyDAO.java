package com.filmbooking.dao;

import java.util.List;

public interface IManyToManyDAO<O, T> {
    List<T> getAllTByO(O o);
    List<O> getAllOByT(T t);
    T getTByID(String oID, String tID);
    O getOByID(String oID, String tID);
    void save(O o, T t);
    void delete(O o, T t);
}
