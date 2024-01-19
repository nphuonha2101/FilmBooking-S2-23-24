package com.filmbooking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "film_bookings")
public class FilmBooking implements Cloneable {
    @Column(name = "film_booking_id", insertable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long filmBookingID;
    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;
    @ManyToOne
    @JoinColumn(name = "username")
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
    @Column(name ="payment_status")
    private String paymentStatus;
    @Transient
    private LocalDateTime expireDate;
    @Transient
    private String vnpayTxnRef;
    
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
        this.vnpayTxnRef = String.valueOf((int) Math.floor(Math.random() * 1000000000));
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
        this.expireDate = bookingDate.plusMinutes(1);
    }

    public String[] getSeats() {
        this.seats = seatsData.split(", ");
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
        if (seatsData != null)
            this.seats = seatsData.split(", ");
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void resetFilmBooking() {
        this.filmBookingID = 0;
        this.bookingDate = null;
        this.seats = new String[0];
        this.showtime = null;
        this.totalFee = 0;
        this.paymentStatus = null;
        this.expireDate = null;
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

    @Override
    public FilmBooking clone() {
        try {
            FilmBooking clone = (FilmBooking) super.clone();
            clone.setShowtime(this.showtime);
            clone.setUser(this.user);
            clone.setBookingDate(this.bookingDate);
            clone.setSeatsData(this.seatsData);
            clone.setTotalFee(this.totalFee);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public boolean isExpired() {
        return this.expireDate.isBefore(LocalDateTime.now());
    }

    public String getVnpayTxnRef() {
        return vnpayTxnRef;
    }

    public void createNewVNPayTxnRef() {
        this.vnpayTxnRef = String.valueOf((int) Math.floor(Math.random() * 1000000000));
    }


}
