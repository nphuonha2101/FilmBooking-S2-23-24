package com.filmbooking.services.impls;

import com.filmbooking.dao.FilmDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Genre;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FilmServicesImpl implements IFilmServices {
    private final IDAO<Film> filmDAO;

    public FilmServicesImpl() {
        filmDAO = FilmDAOImpl.getInstance();
    }

    @Override
    public void openSession() {
        filmDAO.openSession();
    }

    @Override
    public void closeSession() {
        filmDAO.closeSession();
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
        this.openSession();

        name = name.toLowerCase();
        List<Film> result = new ArrayList<>();

        for (Film film : this.getAll()) {
            if (film.getFilmName().toLowerCase().contains(name))
                result.add(film);
        }

        this.closeSession();
        return result;
    }

    @Override
    public HashMap<Long, Film> getFilmAndFilmID() {
        this.openSession();

        HashMap<Long, Film> result = new HashMap<>();
        for (Film film : this.getAll()) {
            result.put(film.getFilmID(), film);
        }

        this.closeSession();
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
            genreList.add(new GenreServicesImpl().getByID(genreID));
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
            genreList.add(new GenreServicesImpl().getByID(genreID));
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
        filmServices.openSession();
        System.out.println(filmServices.getAll());
        filmServices.closeSession();
    }
}
