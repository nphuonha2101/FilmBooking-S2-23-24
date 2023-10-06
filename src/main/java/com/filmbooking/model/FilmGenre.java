package com.filmbooking.model;

public class FilmGenre {
    private String filmID;
    private String genreID;


    public FilmGenre(String filmID, String genreID) {
        this.filmID = filmID;
        this.genreID = genreID;
    }


    public String getFilmID() {
        return filmID;
    }

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }
}
