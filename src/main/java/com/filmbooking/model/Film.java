package com.filmbooking.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "film")
public class Film {
    @Column(name = "film_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String filmID;
    @Column(name = "film_name")
    private String filmName;
    @Column(name = "film_price")
    private double filmPrice;
    @Column(name = "film_director")
    private String director;
    private String cast;
    @Column(name = "film_length")
    private int filmLength;
    @Column(name = "film_description")
    private String filmDescription;
    @Column(name = "film_trailer_link")
    private String filmTrailerLink;
    @Column(name = "img_path")
    private String imgPath;
    @ManyToMany
    @JoinTable(
            name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genreList;
    @OneToMany(mappedBy = "film")
    private List<Showtime> showtimeList;

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
    }

    /**
     * For get from database constructor
     */
    public Film(String filmID, String filmName, double filmPrice, String director, String cast, int filmLength,
                String filmDescription, String filmTrailerLink, String imgPath, List<Genre> genreList, List<Showtime> showtimeList) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.filmPrice = filmPrice;
        this.director = director;
        this.cast = cast;
        this.filmLength = filmLength;
        this.filmDescription = filmDescription;
        this.filmTrailerLink = filmTrailerLink;
        this.imgPath = imgPath;
        this.genreList = genreList;
        this.showtimeList = showtimeList;
    }

    /**
     * For get film from {@link com.filmbooking.dao.FilmGenreDAOImpl#getAllOByT(Genre)} and {@link com.filmbooking.dao.FilmGenreDAOImpl#getOByID(String, String)}
     */
    public Film(String filmID, String filmName, double filmPrice, String director, String cast, int filmLength, String filmDescription, String filmTrailerLink, String imgPath) {
        this.filmID = filmID;
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
    }


    public String getFilmID() {
        return filmID;
    }

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Film) {
            Film film = (Film) obj;
            return this.filmID.equals(film.getFilmID())
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

    public static void main(String[] args) {
        Film film = new Film("1", "2", 3, "4", "5", 6, "7", "8", "9");
        Film film1 = new Film("1", "2", 3, "4", "5", 6, "7", "8", "9");

        System.out.println(film.equals(film1));
    }
}