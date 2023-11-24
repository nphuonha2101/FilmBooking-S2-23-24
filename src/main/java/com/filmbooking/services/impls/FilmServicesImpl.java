package com.filmbooking.services.impls;

import com.filmbooking.dao.FilmDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmGenre;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmGenreServices;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IGetObjectAndObjectIDService;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmGenreServicesImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilmServicesImpl implements IFilmServices, IGetObjectAndObjectIDService<Film> {
    private final IDAO<Film> filmDAO;
    private final IFilmGenreServices filmGenreServices;
    private final IShowtimeServices showtimeServices;

    public FilmServicesImpl() {
        filmDAO = new FilmDAOImpl();
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
    public void save(Film film, String... genres) {
        save(film);
        for (String genre : genres
        ) {
            FilmGenre filmGenre = new FilmGenre(film.getFilmID(), genre);
            filmGenreServices.save(filmGenre);
        }
    }

    @Override
    public void update(Film film) {
        filmDAO.update(film);
    }

    @Override
    public void update(Film film, String... genreIDs) {
        update(film);

        filmGenreServices.deleteAll(film.getFilmID());

        for (String genreID : genreIDs
        ) {
            FilmGenre filmGenre = new FilmGenre(film.getFilmID(), genreID);
            filmGenreServices.save(filmGenre);
        }
    }

    @Override
    public void delete(Film film) {
        List<FilmGenre> filmGenreList = filmGenreServices.getAll();
        List<Showtime> showtimeList = showtimeServices.getAll();

        for (Showtime showtimeInList : showtimeList) {
            if (showtimeInList.getFilmID().equalsIgnoreCase(film.getFilmID()))
                showtimeServices.delete(showtimeInList);
        }

        for (FilmGenre filmGenreInList : filmGenreList) {
            if (filmGenreInList.getFilmID().equalsIgnoreCase(film.getFilmID())) {
                filmGenreServices.delete(filmGenreInList);
            }
        }
        filmDAO.delete(film);
    }

    @Override
    public String getObjectID(Film obj) {
        return obj.getFilmID();
    }

}
