package com.filmbooking.services.impls;

import com.filmbooking.dao.GenericDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IGenreServices;

import java.util.ArrayList;
import java.util.List;

public class FilmServicesImpl implements IFilmServices {
    private final IDAO<Film> filmDAO;
    private final IGenreServices genreServices;

    public FilmServicesImpl() {
        filmDAO = new GenericDAOImpl<>(Film.class);
        genreServices = new GenreServicesImpl();
    }

    public FilmServicesImpl(HibernateSessionProvider sessionProvider) {
        filmDAO = new GenericDAOImpl<>(Film.class);
        genreServices = new GenreServicesImpl(sessionProvider);
        filmDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        filmDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public long getTotalRecords() {
        return filmDAO.getTotalRecords();
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
        return filmDAO.getByID(id, true);
    }

    @Override
    public List<Film> searchFilms(String keyword, double beginPrice, double endPrice) {
        keyword = keyword.toLowerCase();
        List<Film> result = new ArrayList<>();

        for (Film film : this.getAll()) {
            // Check if keyword is not blank then check if film name contains keyword
            if (!keyword.isBlank()) {
                if (film.toString().toLowerCase().contains(keyword)) {
                    if (endPrice > 0) {
                        if (film.getFilmPrice() >= beginPrice && film.getFilmPrice() <= endPrice) {
                            result.add(film);
                        }
                    } else {
                        if (film.getFilmPrice() >= beginPrice) {
                            result.add(film);
                        }
                    }
                }
            }
            // if keyword is blank then find with price
            else {
                if (endPrice > 0) {
                    if (film.getFilmPrice() >= beginPrice && film.getFilmPrice() <= endPrice) {
                        result.add(film);
                    }
                } else {
                    if (film.getFilmPrice() >= beginPrice) {
                        result.add(film);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<Film> getByOffset(int offset, int limit) {
        return filmDAO.getByOffset(offset, limit);
    }

    @Override
    public boolean save(Film film) {
        return filmDAO.save(film);
    }

    @Override
    public boolean save(Film film, String... genreIDs) {

        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.getByID(genreID));
        }
        film.setGenreList(genreList);
        return filmDAO.save(film);
    }

    @Override
    public boolean update(Film film) {
        return filmDAO.update(film);
    }

    @Override
    public boolean update(Film film, String... genreIDs) {

        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.getByID(genreID));
        }
        film.setGenreList(genreList);

        return filmDAO.update(film);
    }

    @Override
    public boolean delete(Film film) {
        return filmDAO.delete(film);
    }


}
