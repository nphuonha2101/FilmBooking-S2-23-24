package com.filmbooking.services;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmGenre;

import java.util.List;

public interface IFilmGenreServices {
    List<FilmGenre> getAll();
    FilmGenre getByID(String id);
    List<FilmGenre> getAllByFilmID(String filmID);
    void save(FilmGenre filmGenre);
    void update(FilmGenre filmGenre);
    void delete(FilmGenre filmGenre);
    void deleteAll(String filmID);

}
