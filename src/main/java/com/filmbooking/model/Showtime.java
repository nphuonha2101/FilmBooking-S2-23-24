package com.filmbooking.model;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.repository.FilmBookingRepository;
import com.filmbooking.repository.FilmRepository;
import com.filmbooking.repository.RoomRepository;
import com.filmbooking.utils.StringUtils;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@TableName("showtimes")
@TableIdName("showtime_id")
@IdAutoIncrement
@AllArgsConstructor
public class Showtime extends AbstractModel implements IModel {
    public static final String TABLE_NAME = "showtimes";

    @Expose
    private long showtimeID;
    @Expose
    private long filmId;
    @Expose
    private long roomId;
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
        this.filmId = film.getFilmID();
        this.roomId = room.getRoomID();
        this.showtimeDate = showtimeDate;
        this.seatsData = room.getSeatData();
        this.filmBookingList = new ArrayList<>();
        this.slug = StringUtils.createSlug(film.getFilmName() + " " + room.getRoomName() + " " + this.getShowtimeDate(), 60);
    }

    /**
     * Use for retrieve data from database
     */
    public Showtime(long showtimeID, long filmId, long roomId, LocalDateTime showtimeDate,
                    String seatsData, String slug, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.showtimeID = showtimeID;
        this.filmId = filmId;
        this.roomId = roomId;
        this.showtimeDate = showtimeDate;
        this.seatsData = seatsData;
        this.slug = slug;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public List<FilmBooking> getFilmBookingList() {
        if (this.filmBookingList == null)
            this.filmBookingList = new FilmBookingRepository().sellectAllByShowtimeId(this.showtimeID);
        return filmBookingList;
    }

    public Film getFilm() {
        return  new FilmRepository().select(this.filmId);
    }

    public void setFilm(Film film) {
        this.filmId = film.getFilmID();
        this.slug = StringUtils.createSlug(film.getFilmName() + " " + getRoom().getRoomName() + " " + this.getShowtimeDate(), 60);
    }

    public Room getRoom() {
        return new RoomRepository().select(this.roomId);
    }

    public void setRoom(Room room) {
        this.roomId = room.getRoomID();
        this.slug = StringUtils.createSlug(getFilm().getFilmName() + " " + room.getRoomName() + " " + this.getShowtimeDate(), 60);
    }

    public void setShowtimeDate(LocalDateTime showtimeDate) {
        this.showtimeDate = showtimeDate;
        this.slug = StringUtils.createSlug(getFilm().getFilmName() + " " + getRoom().getRoomName() + " " + this.getShowtimeDate(), 60);
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
    public Object getIdValue() {
        return this.showtimeID;
    }


}
