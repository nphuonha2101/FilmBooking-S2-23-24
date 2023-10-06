package com.filmbooking.services;

import com.filmbooking.dao.FilmGenreDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.FilmGenre;

import java.util.List;

public class FilmGenreServicesImpl implements IFilmGenreServices{
    private IDAO<FilmGenre> filmGenreDAO;

     public FilmGenreServicesImpl() {
         filmGenreDAO = new FilmGenreDAOImpl();
//         getAll();
     }
    @Override
    public List<FilmGenre> getAll() {
        return filmGenreDAO.getAll();
    }

    @Override
    public FilmGenre getByID(String id) {
        return filmGenreDAO.getByID(id);
    }

    @Override
    public void save(FilmGenre filmGenre) {
        filmGenreDAO.save(filmGenre);
    }

    @Override
    public void update(FilmGenre filmGenre) {
        filmGenreDAO.update(filmGenre);
    }

    @Override
    public void delete(FilmGenre filmGenre) {
        filmGenreDAO.delete(filmGenre);
    }
}
