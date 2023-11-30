package com.filmbooking.services;

import com.filmbooking.model.Theater;

import java.util.HashMap;
import java.util.List;

public interface ITheaterServices {
    void openSession();
    void closeSession();
    List<Theater> getAll();
    Theater getByID(String id);
    HashMap<String, Theater> getTheaterAndTheaterID();
    void save(Theater theater);
    void update(Theater theater);
    void delete(Theater theater);
}
