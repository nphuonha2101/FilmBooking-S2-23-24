package com.filmbooking.model;

public class GenreDetail {
    private String genreID;
    private String genreName;
    private String filmID;


    public GenreDetail(String genreID, String genreName, String filmID) {
        this.genreID = genreID;
        this.genreName = genreName;
        this.filmID = filmID;
    }


    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getFilmID() {
        return filmID;
    }

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }
}
