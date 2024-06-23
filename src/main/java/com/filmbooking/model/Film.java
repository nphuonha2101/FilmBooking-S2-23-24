package com.filmbooking.model;


import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.repository.FilmVoteRepository;
import com.filmbooking.repository.GenreRepository;
import com.filmbooking.repository.ShowtimeRepository;
import com.filmbooking.utils.StringUtils;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@TableName("films")
@TableIdName("film_id")
@IdAutoIncrement
@AllArgsConstructor
public class Film implements IModel {
    public static final String TABLE_NAME = "films";
    @Expose
    private long filmID;
    @Expose
    private String filmName;
    @Expose
    private double filmPrice;
    @Expose
    private String director;
    @Expose
    private String cast;
    @Expose
    private int filmLength;
    @Expose
    private String filmDescription;
    @Expose
    private String filmTrailerLink;
    @Expose
    private String imgPath;
    @Expose
    private String slug;

    private List<Genre> genreList;
    private List<Showtime> showtimeList;
    private List<FilmVote> filmVoteList;

    /**
     * For add new Film constructor
     */
    public Film(String filmName, double filmPrice, String director, String cast, int filmLength, String filmDescription, String filmTrailerLink, String imgPath) {
        this.filmName = filmName;
        this.filmPrice = filmPrice;
        this.director = director;
        this.cast = cast;
        this.filmLength = filmLength;
        this.filmDescription = filmDescription;
        this.filmTrailerLink = filmTrailerLink;
        this.imgPath = imgPath;
        this.genreList = null;
        this.showtimeList = null;
        this.filmVoteList = null;
        this.slug = StringUtils.createSlug(this.filmName, 50);
    }

    public Film(long filmID, String filmName, double filmPrice, String director, String cast, int filmLength, String filmDescription, String filmTrailerLink, String imgPath, String slug) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.filmPrice = filmPrice;
        this.director = director;
        this.cast = cast;
        this.filmLength = filmLength;
        this.filmDescription = filmDescription;
        this.filmTrailerLink = filmTrailerLink;
        this.imgPath = imgPath;
        this.slug = slug;
    }

    public String getFilmVoteScoresStr() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double totalScores = 0;

        getFilmVoteList();

        if (!this.filmVoteList.isEmpty()) {
            for (FilmVote filmVote : this.filmVoteList) {
                totalScores += filmVote.getScores();
            }
            return decimalFormat.format(totalScores / this.filmVoteList.size());
        }

        return String.valueOf(totalScores);
    }

    public String getFilmGenresStr() {
        getGenreList();

        StringBuilder result = new StringBuilder();
        for (Genre genre : this.genreList) {
            result.append(genre.getGenreName()).append(" ");
        }
        return result.toString().trim();
    }


    public List<FilmVote> getFilmVoteList() {
        if (this.filmVoteList == null)
            this.filmVoteList = new FilmVoteRepository().selectAllByFilmId(this.filmID);
        return this.filmVoteList;
    }

    public List<Genre> getGenreList() {
        if (this.genreList == null)
            this.genreList = new GenreRepository().selectAllByFilmId(this.filmID);
        return this.genreList;
    }

    public List<Showtime> getShowtimeList() {
        if (this.showtimeList == null)
            this.showtimeList = new ShowtimeRepository().selectAllByFilmId(this.filmID);
        return this.showtimeList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Film film) {
            return this.filmID == film.getFilmID()
                    && this.filmName.equals(film.getFilmName())
                    && this.filmPrice == film.getFilmPrice()
                    && this.director.equals(film.getDirector())
                    && this.cast.equals(film.getCast())
                    && this.filmLength == film.getFilmLength()
                    && this.filmDescription.equals(film.getFilmDescription())
                    && this.filmTrailerLink.equals(film.getFilmTrailerLink())
                    && this.imgPath.equals(film.getImgPath());
        }
        return false;
    }

    @Override
    public Object getIdValue() {
        return this.filmID;
    }
}