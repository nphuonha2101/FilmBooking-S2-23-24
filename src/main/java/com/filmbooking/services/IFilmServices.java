package com.filmbooking.services;

import com.filmbooking.model.Film;

import java.util.HashMap;
import java.util.List;

public interface IFilmServices {
    void openSession();
    void closeSession();
    List<Film> getAll();
    Film getByFilmID(String id);
    List<Film> getByFilmName(String name);
    HashMap<Long, Film> getFilmAndFilmID();
    void save(Film film);
    void save(Film film, String ...genreIDs);
    void update(Film film);
    void update(Film film, String ...genreIDs);
    void delete(Film film);

}
