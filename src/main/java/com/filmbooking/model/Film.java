package com.filmbooking.model;

public class Film {
    private String filmID;
    private String filmName;
    private double filmPrice;
    private String director;
    private String cast;
    private int filmLength;
    private String filmDescription;
    private String filmTrailerLink;
    private String imgPath;


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
}