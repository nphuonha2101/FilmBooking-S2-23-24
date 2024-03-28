package com.filmbooking.model;

import com.filmbooking.utils.StringUtils;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.List;

@Entity
@Table(name = "films")
public class Film {
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
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Showtime> showtimeList;
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
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

    public long getFilmID() {
        return filmID;
    }

    public void setFilmID(long filmID) {
        this.filmID = filmID;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
        this.slug = StringUtils.createSlug(filmName, 50);
    }

    public double getFilmPrice() {
        return filmPrice;
    }

    public void setFilmPrice(double filmPrice) {
        this.filmPrice = filmPrice;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public int getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(int filmLength) {
        this.filmLength = filmLength;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getFilmTrailerLink() {
        return filmTrailerLink;
    }

    public void setFilmTrailerLink(String filmTrailerLink) {
        this.filmTrailerLink = filmTrailerLink;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public List<Showtime> getShowtimeList() {
        return showtimeList;
    }

    public void setShowtimeList(List<Showtime> showtimeList) {
        this.showtimeList = showtimeList;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<FilmVote> getFilmVoteList() {
        return filmVoteList;
    }

    public void setFilmVoteList(List<FilmVote> filmVoteList) {
        this.filmVoteList = filmVoteList;
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
}