package com.filmbooking.services;

import com.filmbooking.model.Genre;

import java.util.List;

public interface IGenreServices {
    void openSession();
    void closeSession();
    List<Genre> getAll();
    Genre getByID(String id);
    void save(Genre genre);
    void update(Genre genre);
    void delete(Genre genre);
}
