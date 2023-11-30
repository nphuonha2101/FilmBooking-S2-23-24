package com.filmbooking.services.impls;

import com.filmbooking.dao.FilmGenreDAOImpl;
import com.filmbooking.dao.IManyToManyDAO;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.services.IFilmGenreServices;

import java.util.List;

public class FilmGenreServicesImpl implements IFilmGenreServices {
    private IManyToManyDAO<Film, Genre> filmGenreDAO;

    public FilmGenreServicesImpl() {
        filmGenreDAO = FilmGenreDAOImpl.getInstance();
    }


    @Override
    public List<Genre> getAllGenreByFilm(Film film) {
        return filmGenreDAO.getAllTByO(film);
    }

    @Override
    public List<Film> getAllFilmByGenre(Genre genre) {
        return filmGenreDAO.getAllOByT(genre);
    }

    @Override
    public Genre getGenreByID(String filmID, String genreID) {
        return filmGenreDAO.getTByID(filmID, genreID);
    }

    @Override
    public Film getFilmByID(String filmID, String genreID) {
        return filmGenreDAO.getOByID(filmID, genreID);
    }

    @Override
    public void save(Film film, Genre genre) {
        filmGenreDAO.save(film, genre);
    }

    @Override
    public void delete(Film film, Genre genre) {
        filmGenreDAO.delete(film, genre);
    }

    @Override
    public void deleteAllGenresOfFilm(Film film) {
        film.getGenreList().forEach(genre -> this.delete(film, genre));
    }
}
