package com.filmbooking.DAOservices;

import com.filmbooking.model.FilmGenre;

import java.util.List;

public interface IFilmGenreDAOServices {
    List<FilmGenre> getAll();
    boolean saveFilmGenre(FilmGenre filmGenre);
    List<FilmGenre> getFilmGenreByFilmID(String filmID);
    void removeFilmGenresWithFilmID(String filmID);
    void removeFilmGenreWithFilmIDAndGenreID(String filmID, String genreID);
    void changeGenre(String filmID, String genreID);
}
