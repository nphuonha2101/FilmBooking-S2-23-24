package com.filmbooking.model;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.utils.StringUtils;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@TableName("showtimes")
@TableIdName("showtime_id")
@IdAutoIncrement
@AllArgsConstructor
public class Showtime implements IModel {
    public static final String TABLE_NAME = "showtimes";

    @Expose
    private long showtimeID;
    @Expose
    private Film film;
    @Expose
    private Room room;
    @Expose
    private LocalDateTime showtimeDate;
    @Expose
    private String seatsData;
    private List<FilmBooking> filmBookingList;
    @Expose
    private String slug;

    public Showtime() {
    }

    public Showtime(Film film, Room room, LocalDateTime showtimeDate) {
        this.film = film;
        this.room = room;
        this.showtimeDate = showtimeDate;
        this.seatsData = room.getSeatData();
        this.filmBookingList = new ArrayList<>();
        this.slug = StringUtils.createSlug(this.film.getFilmName() + " " + this.room.getRoomName() + " " + this.getShowtimeDate(), 60);
    }


    public void setFilm(Film film) {
        this.film = film;
        this.slug = StringUtils.createSlug(this.film.getFilmName() + " " + this.room.getRoomName() + " " + this.getShowtimeDate(), 60);
    }

    public void setRoom(Room room) {
        this.room = room;
        this.slug = StringUtils.createSlug(this.film.getFilmName() + " " + this.room.getRoomName() + " " + this.getShowtimeDate(), 60);
    }

    public void setShowtimeDate(LocalDateTime showtimeDate) {
        this.showtimeDate = showtimeDate;
        this.slug = StringUtils.createSlug(this.film.getFilmName() + " " + this.room.getRoomName() + " " + this.getShowtimeDate(), 60);
    }


    public void setSeatsMatrix(String[][] seatsMatrix) {
        this.seatsData = StringUtils.arr2DToString(seatsMatrix);
    }


    /**
     * Book seats
     * <br>
     * The seats were booked after user payment successfully!
     * <br>
     * seat = "0" means available seat
     * <br>
     * seat = "1" means booked seat
     * <br>
     * seat = "2" means reserve seat
     * <br>
     *
     * @param bookedSeats is the String array of seats name that user want to book. Example: ["1 2", "2 3", "3 4"]
     */
    public synchronized boolean bookSeats(String[] bookedSeats) {
        String[][] seatsMatrix = StringUtils.convertTo2DArr(this.seatsData);
        for (String seat : bookedSeats) {
            int row = Integer.parseInt(seat.split(" ")[0]);
            int col = Integer.parseInt(seat.split(" ")[1]);

            if (seatsMatrix[row][col].equals("1") || seatsMatrix[row][col].equals("2"))
                return false;

            seatsMatrix[row][col] = "1";
        }
        this.seatsData = StringUtils.arr2DToString(seatsMatrix);
        return true;
    }

    /**
     * Reserve seats
     * <br>
     * The seats were reserve after user chooses seats and before user payment successfully!
     * <br>
     * seat = "0" means available seat
     * <br>
     * seat = "1" means booked seat
     * <br>
     * seat = "2" means reserve seat
     * <br>
     *
     * @param reverseSeats is the String array of seats name that user want to reserve. Example: ["1 2", "2 3", "3 4"]
     */
    public synchronized boolean reserveSeats(String[] reverseSeats) {
        String[][] seatsMatrix = StringUtils.convertTo2DArr(this.seatsData);
        for (String seat : reverseSeats) {
            int row = Integer.parseInt(seat.split(" ")[0]);
            int col = Integer.parseInt(seat.split(" ")[1]);

            if (seatsMatrix[row][col].equals("1") || seatsMatrix[row][col].equals("2"))
                return false;

            seatsMatrix[row][col] = "2";
        }

        this.seatsData = StringUtils.arr2DToString(seatsMatrix);
        return true;
    }

    /**
     * Release seats
     * <br>
     * The seats were release if user payment failed or timeout!
     * <br>
     * seat = "0" means available seat
     * <br>
     * seat = "1" means booked seat
     * <br>
     * seat = "2" means reserve seat
     * <br>
     *
     * @param releaseSeats is the String array of seats name that user want to release. Example: ["1 2", "2 3", "3 4"]
     */
    public synchronized boolean releaseSeats(String[] releaseSeats) {
        String[][] seatsMatrix = StringUtils.convertTo2DArr(this.seatsData);
        for (String seat : releaseSeats) {
            int row = Integer.parseInt(seat.split(" ")[0]);
            int col = Integer.parseInt(seat.split(" ")[1]);

            if (!seatsMatrix[row][col].equals("2"))
                return false;

            seatsMatrix[row][col] = "0";
        }
        this.seatsData = StringUtils.arr2DToString(seatsMatrix);
        return true;
    }


    public int countAvailableSeats() {
        System.out.println(seatsData);
        String seatMatrix[][] = StringUtils.convertTo2DArr(this.seatsData);

        int count = 0;
        for (String[] row : seatMatrix) {
            for (String s : row) {
                if (s.equalsIgnoreCase("0"))
                    count++;
            }
        }
        return count;
    }

    public String[][] getSeatsMatrix() {
        return StringUtils.convertTo2DArr(this.seatsData);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Showtime showtime) {
            return this.showtimeID == showtime.getShowtimeID()
                    && this.film.equals(showtime.getFilm())
                    && this.room.equals(showtime.getRoom())
                    && this.showtimeDate.equals(showtime.getShowtimeDate())
                    && this.seatsData.equals(showtime.getSeatsData());
        }
        return false;
    }

    @Override
    public String getStringID() {
        return String.valueOf(this.showtimeID);
    }

    public Map<String, Object> mapToRow() {
        return Map.of(
                "showtime_id", this.showtimeID,
                "film_id", this.film.getFilmID(),
                "room_id", this.room.getRoomID(),
                "showtime_date", this.showtimeDate,
                "seats_data", this.seatsData,
                "slug", this.slug
        );
    }

}
