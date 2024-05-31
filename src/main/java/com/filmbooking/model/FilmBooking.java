package com.filmbooking.model;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

@TableName("film_bookings")
@TableIdName("film_booking_id")
@IdAutoIncrement
@AllArgsConstructor
public class FilmBooking implements Cloneable, IModel {
    public static final String TABLE_NAME = "film_bookings";
    private static final int EXPIRE_TIME = 15;

    @Getter
    @Setter
    @Expose
    private long filmBookingID;
    @Getter
    @Setter
    @Expose
    private Showtime showtime;
    @Getter
    @Setter
    @Expose
    private User user;
    @Getter
    @Expose
    private LocalDateTime bookingDate;
    @Expose
    private String[] bookedSeats;
    @Getter
    @Expose
    private String seatsData;
    @Setter
    @Getter
    private double totalFee;
    @Getter
    @Setter
    private String paymentStatus;
    private LocalDateTime expireDate;
    @Getter
    private String vnpayTxnRef;

    public FilmBooking(Showtime showtime, User user, LocalDateTime bookingDate, String[] bookedSeats, double totalFee) {
        this.showtime = showtime;
        this.user = user;
        this.bookingDate = bookingDate;
        this.bookedSeats = bookedSeats;
        this.seatsData = String.join(", ", bookedSeats);
        this.totalFee = totalFee;
    }

    public FilmBooking() {
        this.filmBookingID = 0;
        this.showtime = null;
        this.user = null;
        this.bookingDate = null;
        this.bookedSeats = null;
        this.vnpayTxnRef = String.valueOf((int) Math.floor(Math.random() * 1000000000));
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
        this.expireDate = bookingDate.plusMinutes(EXPIRE_TIME);
    }

    public String[] getBookedSeats() {
        if (this.seatsData != null) {
            this.bookedSeats = seatsData.split(", ");
        }
        return this.bookedSeats;
    }

    public void setBookedSeats(String[] bookedSeats) {
        this.bookedSeats = bookedSeats;
        this.seatsData = String.join(", ", bookedSeats);
    }

    public void setSeatsData(String seatsData) {
        this.seatsData = seatsData;
        if (seatsData != null)
            this.bookedSeats = seatsData.split(", ");
    }

    public void resetFilmBooking() {
        this.filmBookingID = 0;
        this.bookingDate = null;
        this.bookedSeats = new String[0];
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

    /**
     * Determines if FilmBooking is expired (default is 15 minutes)
     *
     * @return true of FilmBooking is expired
     */
    public boolean isExpired() {
        return this.expireDate.isBefore(LocalDateTime.now());
    }

    /**
     * Each FilmBooking has an VNPayTxnRef, if payment with VNPay successful then change the VNPayTxnRef
     */
    public void createNewVNPayTxnRef() {
        this.vnpayTxnRef = String.valueOf((int) Math.floor(Math.random() * 1000000000));
    }

    @Override
    public String getStringID() {
        return String.valueOf(this.filmBookingID);
    }

    public Map<String, Object> mapToRow() {
        return Map.of(
                "film_booking_id", this.filmBookingID,
                "showtime_id", this.showtime.getShowtimeID(),
                "username", this.user.getUsername(),
                "booking_date", this.bookingDate,
                "seats", this.seatsData,
                "total_fee", this.totalFee,
                "payment_status", this.paymentStatus
        );
    }

}
