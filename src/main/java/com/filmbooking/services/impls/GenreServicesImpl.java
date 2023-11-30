package com.filmbooking.services.impls;

import com.filmbooking.dao.GenreDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Genre;
import com.filmbooking.services.IGenreServices;

import java.util.List;

public class GenreServicesImpl implements IGenreServices {
    private final IDAO<Genre> genreDAO;

    public GenreServicesImpl() {
        genreDAO = GenreDAOImpl.getInstance();
    }

    @Override
    public void openSession() {
        genreDAO.openSession();
    }

    @Override
    public void closeSession() {
        genreDAO.closeSession();
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
