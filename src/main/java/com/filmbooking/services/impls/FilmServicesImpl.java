package com.filmbooking.services.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.filmbooking.cache.CacheManager;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.repository.CacheRepository;
import com.filmbooking.repository.FilmRepository;
import com.filmbooking.services.AbstractService;
import org.glassfish.jaxb.runtime.v2.runtime.output.SAXOutput;

public class FilmServicesImpl extends AbstractService<Film>{
    private final GenreServicesImpl genreServices = new GenreServicesImpl();
    private final FilmVoteServicesImpl filmVoteServices = new FilmVoteServicesImpl();

    public FilmServicesImpl() {
        super(
                new CacheRepository<>(new FilmRepository(),
                        new CacheManager(1, TimeUnit.MINUTES))
        );
    }


    public boolean insert(Film film, String... genreIDs) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.select(genreID));
        }
        film.setGenreList(genreList);

        if (this.repository.insert(film))
            return genreServices.updateFilmGenres(film);

        return false;
    }

    public boolean update(Film film, String... genreIDs) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.select(genreID));
        }

        film.setGenreList(genreList);

        if (this.repository.update(film)) {
            genreServices.updateFilmGenres(film);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Film film) {
        genreServices.deleteFilmGenres(film);
        filmVoteServices.deleteByFilm(film);
        return super.delete(film);
    }

    //    public List<Film> searchFilms(String searchQuery, double beginPriceNumber, double endPriceNumber) {
//        Map<String, Object> conditions = new HashMap<>();
//
//        if (!searchQuery.isBlank()) {
//            conditions.put("filmName_like", searchQuery);
//        }
//        // if searchQuery is blank then find with price
//        if (endPriceNumber > 0) {
//            conditions.put("filmPrice_<=", endPriceNumber);
//        }
//        conditions.put("filmPrice_>=", beginPriceNumber);
//
//
//        return this.getByPredicates(conditions).getMultipleResults();
//    }


}
