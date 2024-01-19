package com.filmbooking.model;

import com.filmbooking.utils.StringUtils;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "showtimes")
public class Showtime {
    @Column(name = "showtime_id", updatable = false, insertable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long showtimeID;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "showtime_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime showtimeDate;
    @Column(name = "seats_data")
    private String seatsData;

    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
    private List<FilmBooking> filmBookingList;
    @Column(name = "slug")
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

    public long getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(long showtimeID) {
        this.showtimeID = showtimeID;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
        this.slug = StringUtils.createSlug(this.film.getFilmName() + " " + this.room.getRoomName() + " " + this.getShowtimeDate(), 60);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
        this.slug = StringUtils.createSlug(this.film.getFilmName() + " " + this.room.getRoomName() + " " + this.getShowtimeDate(), 60);
    }

    public LocalDateTime getShowtimeDate() {
        return showtimeDate;
    }

    public void setShowtimeDate(LocalDateTime showtimeDate) {
        this.showtimeDate = showtimeDate;
        this.slug = StringUtils.createSlug(this.film.getFilmName() + " " + this.room.getRoomName() + " " + this.getShowtimeDate(), 60);
    }

    public String getSeatsData() {
        return seatsData;
    }

    public void setSeatsData(String seatsData) {
        this.seatsData = seatsData;
    }


    public void setSeatsMatrix(String[][] seatsMatrix) {
        this.seatsData = StringUtils.arr2DToString(seatsMatrix);
    }

    public List<FilmBooking> getFilmBookingList() {
        return filmBookingList;
    }

    public void setFilmBookingList(List<FilmBooking> filmBookingList) {
        this.filmBookingList = filmBookingList;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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
     *  @param releaseSeats is the String array of seats name that user want to release. Example: ["1 2", "2 3", "3 4"]
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
}
