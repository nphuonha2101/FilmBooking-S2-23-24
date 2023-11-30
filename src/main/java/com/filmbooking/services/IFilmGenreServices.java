package com.filmbooking.services;

import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;

import java.util.List;

public interface IFilmGenreServices {
    List<Genre> getAllGenreByFilm(Film film);
    List<Film> getAllFilmByGenre(Genre genre);
    Genre getGenreByID(String filmID, String genreID);
    Film getFilmByID(String filmID, String genreID);
    void save(Film film, Genre genre);
    void delete(Film film, Genre genre);
    void deleteAllGenresOfFilm(Film film);
}
