package com.filmbooking.model;

import java.util.Date;

public class ShowtimeView {
    private String showtimeID;
    private String filmName;
    private String roomName;
    private String theaterName;
    private Date showtimeDate;

    public ShowtimeView(String showtimeID, String filmName, String roomName, String theaterName, Date showtimeDate) {
        this.showtimeID = showtimeID;
        this.filmName = filmName;
        this.roomName = roomName;
        this.theaterName = theaterName;
        this.showtimeDate = showtimeDate;
    }

    public String getShowtimeID() {
        return showtimeID;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getRoomName() {
        return roomName;
    }

    public Date getShowtimeDate() {
        return showtimeDate;
    }
}
