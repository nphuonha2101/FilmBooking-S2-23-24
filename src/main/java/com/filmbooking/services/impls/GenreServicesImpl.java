package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Genre;
import com.filmbooking.services.IGenreServices;

import java.util.List;

public class GenreServicesImpl implements IGenreServices {
    private final DataAccessObjects<Genre> genreDataAccessObjects;

    public GenreServicesImpl() {
        genreDataAccessObjects = new DataAccessObjects<>(Genre.class);
    }

    public GenreServicesImpl(HibernateSessionProvider sessionProvider) {
        genreDataAccessObjects = new DataAccessObjects<>(Genre.class);
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        genreDataAccessObjects.setSessionProvider(sessionProvider);
    }

    @Override
    public List<Genre> getAll() {
        return genreDataAccessObjects.getAll().getMultipleResults();
    }

    @Override
    public Genre getByID(String id) {
        return genreDataAccessObjects.getByID(id, false).getSingleResult();
    }

    @Override
    public boolean save(Genre genre) {
        return genreDataAccessObjects.save(genre);
    }

    @Override
    public boolean update(Genre genre) {
        return genreDataAccessObjects.update(genre);
    }

    @Override
    public boolean delete(Genre genre) {
        return genreDataAccessObjects.delete(genre);
    }
}
