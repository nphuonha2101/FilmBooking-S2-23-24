package com.filmbooking.model;

import com.filmbooking.utils.StringUtils;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.text.DecimalFormat;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = Film.TABLE_NAME)
public class Film implements IModel {
    @Transient
    public static final String TABLE_NAME = "films";

    @Expose
    @Column(name = "film_id", updatable = false, insertable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long filmID;
    @Expose
    @Column(name = "film_name")
    private String filmName;
    @Expose
    @Column(name = "film_price")
    private double filmPrice;
    @Expose
    @Column(name = "film_director")
    private String director;
    @Expose
    @Column(name = "film_cast")
    private String cast;
    @Expose
    @Column(name = "film_length")
    private int filmLength;
    @Expose
    @Column(name = "film_description")
    private String filmDescription;
    @Expose
    @Column(name = "film_trailer_link")
    private String filmTrailerLink;
    @Expose
    @Column(name = "img_path")
    private String imgPath;
    @Expose
    @Column(name = "slug")
    private String slug;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "film_genres",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @Expose
    private List<Genre> genreList;
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Showtime> showtimeList;
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FilmVote> filmVoteList;

    public Film() {
    }

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

    public String getFilmVoteScoresStr() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double totalScores = 0;

        if (!this.filmVoteList.isEmpty()) {
            for (FilmVote filmVote : this.filmVoteList) {
                totalScores += filmVote.getScores();
            }
            return decimalFormat.format(totalScores / this.filmVoteList.size());
        }

        return String.valueOf(totalScores);
    }

    public String getFilmGenresStr
            () {
        StringBuilder result = new StringBuilder();
        for (Genre genre : this.genreList) {
            result.append(genre.getGenreName()).append(" ");
        }
        return result.toString().trim();
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
    public String toString() {
        String filmInfo = this.filmName + " | " + this.director + " | " + this.cast;

        StringBuilder result = new StringBuilder(filmInfo + " | ");

        for (Genre genre : this.genreList) {
            result.append(genre.toString()).append(" ");
        }
        return result.toString();
    }

    public static void main(String[] args) {
    }

    @Override
    public String getStringID() {
        return String.valueOf(this.filmID);
    }


}