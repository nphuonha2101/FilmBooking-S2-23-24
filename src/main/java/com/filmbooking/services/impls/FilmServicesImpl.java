package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.dao.daoDecorators.OffsetDAODecorator;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.services.AbstractServices;
import com.filmbooking.services.IServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilmServicesImpl extends AbstractServices<Film> {

    private final GenreServicesImpl genreServices;

    public FilmServicesImpl(HibernateSessionProvider sessionProvider) {
        super.decoratedDAO = new DataAccessObjects<>(Film.class);
        this.genreServices = new GenreServicesImpl(sessionProvider);
        super.setSessionProvider(sessionProvider);
    }

    @Override
    public Film getByID(String id) {
        return super.decoratedDAO.getByID(id, true);
    }

    /**
     * Save a film with its genreIDs
     *
     * @param film     film to save
     * @param genreIDs genreIDs of the film
     * @return true if save successfully, false otherwise
     */
    public boolean save(Film film, String... genreIDs) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.getByID(genreID));
        }
        film.setGenreList(genreList);

        return super.decoratedDAO.save(film);
    }

    /**
     * Update a film with its genreIDs
     *
     * @param film     film to update
     * @param genreIDs genreIDs of the film
     * @return true if update successfully, false otherwise
     */
    public boolean update(Film film, String... genreIDs) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.getByID(genreID));
        }
        film.setGenreList(genreList);

        return super.decoratedDAO.update(film);
    }


}
