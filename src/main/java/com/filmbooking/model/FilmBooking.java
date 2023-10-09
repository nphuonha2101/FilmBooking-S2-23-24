package com.filmbooking.model;

import java.util.Date;

public class FilmBooking {
    String filmBookingID;
    String showtimeID;
    String username;
    Date bookingDate;
    String[] seat;
    double totalPrice;

    public FilmBooking(String filmBookingID, String showtimeID, String username,Date bookingDate, String[] seat, double totalPrice) {
        this.filmBookingID = filmBookingID;
        this.showtimeID = showtimeID;
        this.username = username;
        this.bookingDate = bookingDate;
        this.seat = seat;
        this.totalPrice = totalPrice;
    }

    public String getFilmBookingID() {
        return filmBookingID;
    }

    public void setFilmBookingID(String filmBookingID) {
        this.filmBookingID = filmBookingID;
    }

    public String getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(String showtimeID) {
        this.showtimeID = showtimeID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String[] getSeat() {
        return seat;
    }

    public void setSeat(String[] seat) {
        this.seat = seat;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


}
