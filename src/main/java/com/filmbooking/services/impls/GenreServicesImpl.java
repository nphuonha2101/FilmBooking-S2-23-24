package com.filmbooking.services.impls;

import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.repository.AbstractRepository;
import com.filmbooking.repository.GenreRepository;
import com.filmbooking.services.AbstractService;
import com.filmbooking.services.IService;

public class GenreServicesImpl extends AbstractService<Genre> implements IService<Genre> {

    public GenreServicesImpl() {
        super(new GenreRepository());
    }

    public boolean updateFilmGenres(Film film) {
        return ((GenreRepository) this.repository).updateByFilm(film);
    }
    public boolean deleteFilmGenres(Film film) {
        return ((GenreRepository) this.repository).deleteByFilmId(film.getFilmID());
    }
}
