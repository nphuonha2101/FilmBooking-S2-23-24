package com.filmbooking.model;

public class Film {
    private String filmID;
    private String filmName;
    private double price;
    private String roomID;
    private String genre;
    private String imgPath;

    public Film(String filmID, String filmName, double price, String roomID, String genre, String imgPath) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.price = price;
        this.roomID = roomID;
        this.genre = genre;
        this.imgPath = imgPath;
    }

    // ------ GETTER AND SETTER ------ //

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
