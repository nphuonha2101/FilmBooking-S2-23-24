package com.filmbooking.services.impls;

import com.filmbooking.dao.FilmDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.services.*;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.ArrayList;
import java.util.List;

public class FilmServicesImpl implements IFilmServices {
    private final IDAO<Film> filmDAO;
    private final IGenreServices genreServices;

    public FilmServicesImpl() {
        filmDAO = FilmDAOImpl.getInstance();
        genreServices = new GenreServicesImpl();
    }

    public FilmServicesImpl(HibernateSessionProvider sessionProvider) {
        filmDAO = FilmDAOImpl.getInstance();
        genreServices = new GenreServicesImpl(sessionProvider);
        filmDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        filmDAO.setSessionProvider(sessionProvider);
    }

    /**
     * Get all film genres
     *
     * @return a list of film genres
     */
    @Override
    public List<Film> getAll() {
        return filmDAO.getAll();
    }

    @Override
    public Film getByFilmID(String id) {
        return filmDAO.getByID(id);
    }

    @Override
    public List<Film> getByFilmName(String name) {
        name = name.toLowerCase();
        List<Film> result = new ArrayList<>();

        for (Film film : this.getAll()) {
            if (film.getFilmName().toLowerCase().contains(name))
                result.add(film);
        }
        return result;
    }

    @Override
    public void save(Film film) {
        filmDAO.save(film);
    }

    @Override
    public void save(Film film, String... genreIDs) {

        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.getByID(genreID));
        }
        film.setGenreList(genreList);
        filmDAO.save(film);
    }

    @Override
    public void update(Film film) {
        filmDAO.update(film);
    }

    @Override
    public void update(Film film, String... genreIDs) {

        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.getByID(genreID));
        }
        film.setGenreList(genreList);

        filmDAO.update(film);
    }

    @Override
    public void delete(Film film) {
        filmDAO.delete(film);
    }

    public static void main(String[] args) {
        FilmServicesImpl filmServices = new FilmServicesImpl();

        System.out.println(filmServices.getAll());
    }
}
