package com.filmbooking.services;

import com.filmbooking.dao.FilmGenreDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.FilmGenre;

import java.util.ArrayList;
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
    public List<FilmGenre> getAllByFilmID(String filmID) {
        List<FilmGenre> result = new ArrayList<>();

        for (FilmGenre filmGenre: this.getAll()
             ) {
            if (filmGenre.getFilmID().equalsIgnoreCase(filmID))
                result.add(filmGenre);
        }

        return result;
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

    @Override
    public void deleteAll(String filmID) {
        List<FilmGenre> filmGenreList = this.getAll();

        for (FilmGenre filmGenre: filmGenreList
             ) {
            if (filmGenre.getFilmID().equalsIgnoreCase(filmID)) {
                delete(filmGenre);
            }
        }
    }

}
