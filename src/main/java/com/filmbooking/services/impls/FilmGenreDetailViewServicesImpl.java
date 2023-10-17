package com.filmbooking.services.impls;

import com.filmbooking.dao.view.FilmGenreDetailViewDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.view.FilmGenreDetailView;
import com.filmbooking.services.IFilmGenreDetailViewServices;

import java.util.ArrayList;
import java.util.List;

public class FilmGenreDetailViewServicesImpl implements IFilmGenreDetailViewServices  {
    private final IDAO<FilmGenreDetailView> filmGenreDetailViewDAO;

    public FilmGenreDetailViewServicesImpl() {
        filmGenreDetailViewDAO = new FilmGenreDetailViewDAOImpl();
    }

    @Override
    public List<FilmGenreDetailView> getAll() {
        return filmGenreDetailViewDAO.getAll();
    }

    @Override
    public List<FilmGenreDetailView> getByFilmID(String id) {
        List<FilmGenreDetailView> result = new ArrayList<>();

        for (FilmGenreDetailView filmGenreDetailView: this.getAll()
             ) {
            if (filmGenreDetailView.getFilmID().equalsIgnoreCase(id))
                result.add(filmGenreDetailView);
        }
        return result;
    }
}
