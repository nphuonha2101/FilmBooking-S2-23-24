package com.filmbooking.services;

import com.filmbooking.model.Film;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IFilmServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);
    List<Film> getAll();
    Film getByFilmID(String id);
    List<Film> getByFilmName(String name);

    void save(Film film);
    void save(Film film, String ...genreIDs);
    void update(Film film);
    void update(Film film, String ...genreIDs);
    void delete(Film film);

}
