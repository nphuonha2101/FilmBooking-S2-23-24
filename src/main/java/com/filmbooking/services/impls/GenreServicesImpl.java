package com.filmbooking.services.impls;

import com.filmbooking.dao.GenreDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Genre;
import com.filmbooking.services.IGenreServices;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public class GenreServicesImpl implements IGenreServices {
    private final IDAO<Genre> genreDAO;

    public GenreServicesImpl() {
        genreDAO = GenreDAOImpl.getInstance();
    }

    public GenreServicesImpl(HibernateSessionProvider sessionProvider) {
        genreDAO = GenreDAOImpl.getInstance();
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        genreDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public List<Genre> getAll() {
        return genreDAO.getAll();
    }

    @Override
    public Genre getByID(String id) {
        return genreDAO.getByID(id);
    }

    @Override
    public void save(Genre genre) {
        genreDAO.save(genre);
    }

    @Override
    public void update(Genre genre) {
        genreDAO.update(genre);
    }

    @Override
    public void delete(Genre genre) {
        genreDAO.delete(genre);
    }
}
