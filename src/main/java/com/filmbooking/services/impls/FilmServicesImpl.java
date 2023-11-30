package com.filmbooking.services.impls;

import com.filmbooking.dao.FilmDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FilmServicesImpl implements IFilmServices, IGetObjectAndObjectIDService<Film> {
    private final IDAO<Film> filmDAO;
    private final IFilmGenreServices filmGenreServices;
    private final IGenreServices genreServices;
    private final IShowtimeServices showtimeServices;


    public FilmServicesImpl() {
        filmDAO = FilmDAOImpl.getInstance();
        genreServices = new GenreServicesImpl();
        filmGenreServices = new FilmGenreServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();
    }

    /**
     * Get all film genres
     * @return a list of film genres
     */
    @Override
    public List<Film> getAll() {
        return filmDAO.getAll();
    }

    @Override
    public Film getByFilmID(String id) {
        return filmDAO.getByID(id);
    }

    @Override
    public List<Film> getByFilmName(String name) {
        name = name.toLowerCase();
        List<Film> result = new ArrayList<>();

        for (Film film: this.getAll()) {
            if (film.getFilmName().toLowerCase().contains(name))
                result.add(film);
        }
        return result;
    }

    @Override
    public HashMap<String, Film> getFilmAndFilmID() {
        return this.getObjectAndObjectID(this.getAll());
    }

    @Override
    public void save(Film film) {
        filmDAO.save(film);
    }

    @Override
    public void save(Film film, String... genreIDs) {
        save(film);

        Arrays.stream(genreIDs).forEach(genreID -> {
            Genre genre = genreServices.getByID(genreID);
            filmGenreServices.save(film, genre);
        });
    }

    @Override
    public void update(Film film) {
        filmDAO.update(film);
    }

    @Override
    public void update(Film film, String... genreIDs) {
        update(film);

        filmGenreServices.deleteAllGenresOfFilm(film);

        Arrays.stream(genreIDs).forEach(genreID -> {
            Genre genre = genreServices.getByID(genreID);
            filmGenreServices.save(film, genre);
        });
    }

    @Override
    public void delete(Film film) {
        List<Genre> genreList = film.getGenreList();
        List<Showtime> showtimeList = film.getShowtimeList();

        genreList.stream().forEach(genre -> filmGenreServices.delete(film, genre));
        showtimeList.stream().forEach(showtime -> showtimeServices.delete(showtime));

        filmDAO.delete(film);
    }

    @Override
    public String getObjectID(Film obj) {
        return obj.getFilmID();
    }

}
