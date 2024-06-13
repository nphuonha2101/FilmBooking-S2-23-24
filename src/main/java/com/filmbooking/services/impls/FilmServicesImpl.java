package com.filmbooking.services.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.repository.FilmRepository;
import com.filmbooking.services.AbstractService;

public class FilmServicesImpl extends AbstractService<Film>{
    private final GenreServicesImpl genreServices = new GenreServicesImpl();

    public FilmServicesImpl() {
        super(new FilmRepository());
    }


    public boolean insert(Film film, String... genreIDs) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.select(genreID));
        }
        film.setGenreList(genreList);

        return this.repository.insert(film);
    }

    public boolean update(Film film, String... genreIDs) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreID : genreIDs) {
            genreList.add(genreServices.select(genreID));
        }
        film.setGenreList(genreList);

        return this.repository.update(film);
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
