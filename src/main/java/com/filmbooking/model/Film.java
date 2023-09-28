package com.filmbooking.model;

import java.io.File;

public class Film {
    private String filmID;
    private String filmName;
    private double filmPrice;
    private String director;
    private String actors;
    private int filmLength;
    private String filmDescription;
    private String imgPath;


    public Film(String filmID, String filmName, double filmPrice, String director, String actors, int filmLength, String filmDescription, String imgPath) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.filmPrice = filmPrice;
        this.director = director;
        this.actors = actors;
        this.filmLength = filmLength;
        this.filmDescription = filmDescription;
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

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
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
}