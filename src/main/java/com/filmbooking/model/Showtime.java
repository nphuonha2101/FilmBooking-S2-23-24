package com.filmbooking.model;

import java.time.LocalDateTime;

public class Showtime {
    private String showtimeID;
    private String filmID;
    private String roomID;
    private LocalDateTime showtimeDate;

    public Showtime(String showtimeID, String filmID, String roomID, LocalDateTime showtimeDate) {
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

    public LocalDateTime getShowtimeDate() {
        return showtimeDate;
    }

    public void setShowtimeDate(LocalDateTime showtimeDate) {
        this.showtimeDate = showtimeDate;
    }
}
