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

public class FilmServicesImpl implements IFilmServices, IGetObjectAndObjectIDService<Film> {
    private final IDAO<Film> filmDAO;

    public FilmServicesImpl() {
        filmDAO = FilmDAOImpl.getInstance();
    }

    /**
     * Get all film genres
     *
     * @return a list of film genres
     */
    @Override
    public List<Film> getAll() {
        List<Film> result;
        filmDAO.openSession();
        result = filmDAO.getAll();
        filmDAO.closeSession();
        return result;
    }

    @Override
    public Film getByFilmID(String id) {

        Film result;
        filmDAO.openSession();
        result = filmDAO.getByID(id);
        System.out.println(result);

        filmDAO.closeSession();

        return result;
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
    public HashMap<String, Film> getFilmAndFilmID() {
        return this.getObjectAndObjectID(this.getAll());
    }

    @Override
    public void save(Film film) {
        filmDAO.openSession();
        filmDAO.save(film);
        filmDAO.closeSession();
    }

    @Override
    public void save(Film film, String... genreIDs) {
        filmDAO.openSession();
        filmDAO.save(film);
        filmDAO.closeSession();
    }

    @Override
    public void update(Film film) {
        filmDAO.openSession();
        filmDAO.update(film);
        filmDAO.closeSession();
    }

    @Override
    public void update(Film film, String... genreIDs) {
        filmDAO.openSession();
        filmDAO.update(film);
        filmDAO.closeSession();
    }

    @Override
    public void delete(Film film) {
        filmDAO.openSession();
        filmDAO.delete(film);
        filmDAO.closeSession();

    }

    @Override
    public String getObjectID(Film obj) {
        return obj.getFilmID();
    }

    public static void main(String[] args) {
        FilmServicesImpl filmServices = new FilmServicesImpl();
        System.out.println(filmServices.getAll());
    }
}
