package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.dao.daoDecorators.OffsetDAODecorator;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IGenreServices;

import java.util.ArrayList;
import java.util.List;

public class FilmServicesImpl implements IFilmServices {
    private final DataAccessObjects<Film> filmDataAccessObjects;
    private final IGenreServices genreServices;

    public FilmServicesImpl() {
        filmDataAccessObjects = new DataAccessObjects<>(Film.class);
        genreServices = new GenreServicesImpl();
    }

    public FilmServicesImpl(HibernateSessionProvider sessionProvider) {
        filmDataAccessObjects = new DataAccessObjects<>(Film.class);
        genreServices = new GenreServicesImpl(sessionProvider);
        filmDataAccessObjects.setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        filmDataAccessObjects.setSessionProvider(sessionProvider);
    }

    @Override
    public long getTotalRecords() {
        return filmDataAccessObjects.getTotalRecordRows();
    }

    /**
     * Get all film genres
     *
     * @return a list of film genres
     */
    @Override
    public List<Film> getAll() {
        return filmDataAccessObjects.getAll().getMultipleResults();
    }

    @Override
    public Film getByFilmID(String id) {
        return filmDataAccessObjects.getByID(id, true).getSingleResult();
    }

    @Override
    public Film getBySlug(String slug) {
        for (Film film : filmDataAccessObjects.getAll().getMultipleResults()) {
            if (film.getSlug().equalsIgnoreCase(slug))
                return film;
        }
        return null;
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
        return new OffsetDAODecorator<Film>(filmDataAccessObjects, offset, limit).getAll().getMultipleResults();
    }

    @Override
    public boolean save(Film film) {
        return filmDataAccessObjects.save(film);
    }

    @Override
    public boolean save(Film film, String... genreIDs) {

        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.getByID(genreID));
        }
        film.setGenreList(genreList);
        return filmDataAccessObjects.save(film);
    }

    @Override
    public boolean update(Film film) {
        return filmDataAccessObjects.update(film);
    }

    @Override
    public boolean update(Film film, String... genreIDs) {

        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.getByID(genreID));
        }
        film.setGenreList(genreList);

        return filmDataAccessObjects.update(film);
    }

    @Override
    public boolean delete(Film film) {
        return filmDataAccessObjects.delete(film);
    }


}
