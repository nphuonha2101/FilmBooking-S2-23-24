package com.filmbooking.services;

import com.filmbooking.dao.FilmDAOImpl;
import com.filmbooking.dao.FilmGenreDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmGenre;

import java.util.List;

public class FilmServicesImpl implements IFilmServices {
    private IDAO<Film> filmDAO;
    private IFilmGenreServices filmGenreServices;

    public FilmServicesImpl() {
        filmDAO = new FilmDAOImpl();
        filmGenreServices = new FilmGenreServicesImpl();
//        getAll();
    }

    @Override
    public List<Film> getAll() {
        return filmDAO.getAll();
    }

    @Override
    public Film getByFilmID(String id) {
        getAll();
        return filmDAO.getByID(id);
    }

    @Override
    public void save(Film film) {
        filmDAO.save(film);
    }

    @Override
    public void save(Film film, String... genres) {
        save(film);
        for (String genre: genres
             ) {
            FilmGenre filmGenre = new FilmGenre(film.getFilmID(), genre);
            filmGenreServices.save(filmGenre);
        }
    }

    @Override
    public void update(Film film) {
        filmDAO.update(film);
    }

    @Override
    public void update(Film film, String... genres) {
        update(film);

        for (String genre: genres
             ) {
            FilmGenre filmGenre = new FilmGenre(film.getFilmID(), genre);
            filmGenreServices.update(filmGenre);
        }
    }

    @Override
    public void delete(Film film) {
        List<FilmGenre> filmGenreList = filmGenreServices.getAll();
        for (FilmGenre filmGenreInList : filmGenreList) {
            if (filmGenreInList.getFilmID().equalsIgnoreCase(film.getFilmID())) {
                filmGenreServices.delete(filmGenreInList);
            }
        }
        filmDAO.delete(film);
    }
}
