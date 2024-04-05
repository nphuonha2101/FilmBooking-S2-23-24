package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.services.AbstractCRUDServices;
import com.filmbooking.services.IFilmServices;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmServicesImpl extends AbstractCRUDServices<Film> implements IFilmServices {
    private final GenreServicesImpl genreServices;

    public FilmServicesImpl(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO = new DataAccessObjects<>(Film.class);
        this.genreServices = new GenreServicesImpl(sessionProvider);
        this.setSessionProvider(sessionProvider);
    }

    public FilmServicesImpl() {
        this.decoratedDAO = new DataAccessObjects<>(Film.class);
        this.genreServices = new GenreServicesImpl();
    }

    @Override
    public String getTableName() {
        return Film.TABLE_NAME;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
        this.genreServices.setSessionProvider(sessionProvider);
    }

    @Override
    public Film getByID(String id) {
        return this.decoratedDAO.getByID(id, true);
    }

    @Override
    public boolean save(Film film, String... genreIDs) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.getByID(genreID));
        }
        film.setGenreList(genreList);

        return this.decoratedDAO.save(film);
    }

    @Override
    public boolean update(Film film, String... genreIDs) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.getByID(genreID));
        }
        film.setGenreList(genreList);

        return this.decoratedDAO.update(film);
    }

    public List<Film> searchFilms(String searchQuery, double beginPriceNumber, double endPriceNumber) {
        Map<String, Object> conditions = new HashMap<>();

        if (!searchQuery.isBlank()) {
            conditions.put("filmName_like", searchQuery);
        }
        // if searchQuery is blank then find with price
        if (endPriceNumber > 0) {
            conditions.put("filmPrice_<=", endPriceNumber);
        }
        conditions.put("filmPrice_>=", beginPriceNumber);


        return this.getByPredicates(conditions).getMultipleResults();
    }
}
