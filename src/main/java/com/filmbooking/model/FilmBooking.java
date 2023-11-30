package com.filmbooking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "film_booking")
public class FilmBooking {
    @Column(name = "film_booking_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long filmBookingID;
    @ManyToOne
    private Showtime showtime;
    @ManyToOne
    private User user;
    @Column(name = "booking_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime bookingDate;
    @Transient
    private String[] seats;
    @Column(name = "seats")
    private String seatsData;
    @Column(name = "total_fee")
    private double totalFee;

    public FilmBooking(long filmBookingID, User user, Showtime showtime, LocalDateTime bookingDate,
                       String seatsData, double totalFee) {

        this.filmBookingID = filmBookingID;
        this.showtime = showtime;
        this.user = user;
        this.bookingDate = bookingDate;
        this.seatsData = seatsData;
        this.seats = seatsData.split(", ");
        this.totalFee = totalFee;
    }

    public FilmBooking(Showtime showtime, User user, LocalDateTime bookingDate, String[] seats, double totalFee) {
        this.showtime = showtime;
        this.user = user;
        this.bookingDate = bookingDate;
        this.seats = seats;
        this.seatsData = String.join(", ", seats);
        this.totalFee = totalFee;
    }

    public FilmBooking() {
        this.filmBookingID = 0;
        this.showtime = null;
        this.user = null;
        this.bookingDate = null;
        this.seats = new String[0];
    }

    public long getFilmBookingID() {
        return filmBookingID;
    }

    public void setFilmBookingID(long filmBookingID) {
        this.filmBookingID = filmBookingID;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        this.filmBookingID = 0;
        this.showtime = null;
        this.bookingDate = null;
        this.seats = new String[0];
    }

    @Override
    public String toString() {
        return this.showtime + ", " + this.filmBookingID + ", " + this.user;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FilmBooking filmBooking) {
            return this.filmBookingID == filmBooking.getFilmBookingID()
                    && this.showtime.equals(filmBooking.getShowtime())
                    && this.user.equals(filmBooking.getUser())
                    && this.bookingDate.equals(filmBooking.getBookingDate())
                    && this.totalFee == filmBooking.getTotalFee();
        }
        return false;
    }
}
