package com.filmbooking.model;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.repository.ShowtimeRepository;
import com.filmbooking.repository.UserRepository;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.services.impls.UserServicesImpl;
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
public class FilmBooking extends AbstractModel implements IModel {
    public static final String TABLE_NAME = "film_bookings";
    private static final int EXPIRE_TIME = 15;

    @Getter
    @Setter
    @Expose
    private long filmBookingID;
    @Getter
    @Setter
    @Expose
    private long showtimeId;
    @Setter
    @Expose
    private String username;
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
    @Expose
    private double totalFee;
    @Getter
    @Setter
    @Expose
    private String paymentStatus;
    private LocalDateTime expireDate;
    @Getter
    private String vnpayTxnRef;


    public FilmBooking(Showtime showtime, User user, LocalDateTime bookingDate, String[] bookedSeats, double totalFee) {
        this.showtimeId = showtime.getShowtimeID();
        this.username = user.getUsername();
        this.bookingDate = bookingDate;
        this.bookedSeats = bookedSeats;
        this.seatsData = String.join(", ", bookedSeats);
        this.totalFee = totalFee;
    }

    public FilmBooking(long id, String username, long showtimeId, LocalDateTime bookingDate,
                       String[] bookedSeats, double totalFee, String paymentStatus,
                       LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.filmBookingID = id;
        this.showtimeId = showtimeId;
        this.username = username;
        this.bookingDate = bookingDate;
        this.bookedSeats = bookedSeats;
        this.seatsData = String.join(", ", bookedSeats);
        this.totalFee = totalFee;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public FilmBooking() {
        this.filmBookingID = 0;
        this.showtimeId = 0;
        this.username = null;
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
        this.showtimeId = 0;
        this.totalFee = 0;
        this.paymentStatus = null;
        this.expireDate = null;
    }

    public User getUser() {
        return new UserRepository().select(this.username);
    }

    public Showtime getShowtime() {
        return new ShowtimeRepository().select(this.showtimeId);
    }

    public void setShowtime(Showtime showtime) {
        this.showtimeId = showtime.getShowtimeID();
    }

    public void setUser(User user) {
        this.username = user.getUsername();
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
    public Object getIdValue() {
        return this.filmBookingID;
    }

}
