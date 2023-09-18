package com.filmbooking.DAOservices;

import com.filmbooking.model.Film;

import java.util.List;

public interface IFilmDAOServices {
    List<Film> getAll();
    boolean saveFilm(Film film);
    Film getFilmByID(String filmID);
    List<Film> getFilmByName(String filmName);
    boolean deleteFilm(Film film);

}
