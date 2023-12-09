package com.filmbooking.services;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;

import java.util.List;

public interface IFilmServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);

    long getTotalRecords();

    List<Film> getByOffset(int offset, int limit);

    List<Film> getAll();

    Film getByFilmID(String id);

    List<Film> getByFilmName(String name);

    boolean save(Film film);

    boolean save(Film film, String... genreIDs);

    boolean update(Film film);

    boolean update(Film film, String... genreIDs);

    boolean delete(Film film);


}
