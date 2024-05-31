package com.filmbooking.model;

/*
 *  @created 05/01/2024 - 9:50 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@TableName("film_votes")
@TableIdName("film_vote_id")
@IdAutoIncrement
public class FilmVote implements IModel {
    public static final String TABLE_NAME = "film_votes";

    @Getter
    @Setter
    @Expose
    private Long id;

    @Expose
    private Film film;
    @Expose
    private int scores;

    public FilmVote() {}

    public FilmVote(Film film, int scores) {
        this.film = film;
        this.scores = scores;
    }

    @Override
    public Object getIdValue() {
        return this.id;
    }

    public Map<String, Object> mapToRow() {
        return Map.of(
                "film_vote_id", this.id,
                "film_id", this.film.getFilmID(),
                "scores", this.scores
        );
    }


}
