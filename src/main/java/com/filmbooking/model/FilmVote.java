package com.filmbooking.model;

/*
 *  @created 05/01/2024 - 9:50 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.repository.FilmRepository;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@TableName("film_votes")
@TableIdName("film_vote_id")
@IdAutoIncrement
@AllArgsConstructor
public class FilmVote implements IModel {
    public static final String TABLE_NAME = "film_votes";

    @Getter
    @Setter
    @Expose
    private long id;

    @Expose
    private long filmId;
    @Expose
    private int scores;

    // Temp data
    private Film tempFilm;

    public FilmVote() {
    }

    public FilmVote(Film film, int scores) {
        this.filmId = film.getFilmID();
        this.scores = scores;
    }

    public FilmVote(long id, long filmId, int scores) {
        this.id = id;
        this.filmId = filmId;
        this.scores = scores;
    }

    @Override
    public Object getIdValue() {
        return this.id;
    }

    public Film getFilm() {
        if (this.tempFilm == null)
            this.tempFilm = new FilmRepository().select(this.filmId);
        return this.tempFilm;
    }

    public void setFilm(Film film) {
        this.filmId = film.getFilmID();
    }


}
