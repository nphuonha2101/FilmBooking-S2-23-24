package com.filmbooking.services.impls;

import com.filmbooking.dao.GenreDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Genre;
import com.filmbooking.services.IGenreServices;

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
    public boolean save(Genre genre) {
        return genreDAO.save(genre);
    }

    @Override
    public boolean update(Genre genre) {
        return genreDAO.update(genre);
    }

    @Override
    public boolean delete(Genre genre) {
        return genreDAO.delete(genre);
    }
}
