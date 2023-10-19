package com.filmbooking.model.view;

import java.time.LocalDateTime;
import java.util.Date;

public class ShowtimeView {
    private String showtimeID;
    private String filmName;
    private String roomName;
    private String theaterName;
    private LocalDateTime showtimeDate;

    public ShowtimeView(String showtimeID, String filmName, String roomName, String theaterName, LocalDateTime showtimeDate) {
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

    public String getTheaterName() {
        return theaterName;
    }

    public LocalDateTime getShowtimeDate() {
        return showtimeDate;
    }
}
