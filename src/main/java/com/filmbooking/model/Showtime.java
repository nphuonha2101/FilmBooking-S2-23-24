package com.filmbooking.model;

import java.util.Date;

public class Showtime {
    private String showtimeID;
    private String filmID;
    private String roomID;
    private Date showtimeDate;

    public Showtime(String showtimeID, String filmID, String roomID, Date showtimeDate) {
        this.showtimeID = showtimeID;
        this.filmID = filmID;
        this.roomID = roomID;
        this.showtimeDate = showtimeDate;
    }

    public String getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(String showtimeID) {
        this.showtimeID = showtimeID;
    }

    public String getFilmID() {
        return filmID;
    }

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Date getShowtimeDate() {
        return showtimeDate;
    }

    public void setShowtimeDate(Date showtimeDate) {
        this.showtimeDate = showtimeDate;
    }
}
