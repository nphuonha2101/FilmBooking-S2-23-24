package com.filmbooking.repository;

import com.filmbooking.model.Film;
import com.filmbooking.model.User;
import com.filmbooking.repository.mapper.FilmMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class FilmRepository extends AbstractRepository<Film>{

    public FilmRepository(Class<Film> modelClass) {
        super(modelClass);
    }

    @Override
    RowMapper<Film> getRowMapper() {
        return new FilmMapper();
    }

    @Override
    Map<String, Object> mapToRow(Film film) {
        return Map.of(
                "film_id", film.getFilmID(),
                "film_name", film.getFilmName(),
                "film_price", film.getFilmPrice(),
                "film_director", film.getDirector(),
                "film_cast", film.getCast(),
                "film_length", film.getFilmLength(),
                "film_description", film.getFilmDescription(),
                "film_trailer_link", film.getFilmTrailerLink(),
                "img_path", film.getImgPath(),
                "slug", film.getSlug()
        );
    }
}
