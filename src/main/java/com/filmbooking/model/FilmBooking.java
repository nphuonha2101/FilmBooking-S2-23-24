package com.filmbooking.model;

import java.time.LocalDateTime;

public class FilmBooking {
    String filmBookingID;
    String showtimeID;
    String username;
    LocalDateTime bookingDate;
    String[] seats;
    String seatsData;
    double totalFee;

    public FilmBooking(String filmBookingID, String username, String showtimeID, LocalDateTime bookingDate,
                       String seatsData, double totalFee) {

        this.filmBookingID = filmBookingID;
        this.showtimeID = showtimeID;
        this.username = username;
        this.bookingDate = bookingDate;
        this.seatsData = seatsData;
        this.totalFee = totalFee;
    }

    public FilmBooking(String showtimeID, String username, LocalDateTime bookingDate, String[] seats, double totalFee) {
        this.showtimeID = showtimeID;
        this.username = username;
        this.bookingDate = bookingDate;
        this.seats = seats;
        this.seatsData = String.join(", ", seats);
        this.totalFee = totalFee;
    }

    public FilmBooking() {
        this.filmBookingID = "";
        this.showtimeID = "";
        this.username = "";
        this.bookingDate = null;
        this.seats = new String[0];
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

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String[] getSeats() {
        return seats;
    }

    public void setSeats(String[] seats) {
        this.seats = seats;
        this.seatsData = String.join(", ", seats);
    }

    public String getSeatsData() {
        return seatsData;
    }

    public void setSeatsData(String seatsData) {
        this.seatsData = seatsData;
        this.seats = seatsData.split(", ");
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public void resetFilmBooking() {
        this.filmBookingID = "";
        this.showtimeID = "";
        this.bookingDate = null;
        this.seats = new String[0];
    }


}
