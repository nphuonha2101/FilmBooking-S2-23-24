package com.filmbooking.model;

import com.filmbooking.utils.StringUtils;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "showtime")
public class Showtime {
    @Column(name = "showtime_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long showtimeID;
    @ManyToOne
    private Film film;

    @ManyToOne
    private Room room;
    @Column(name = "showtime_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime showtimeDate;
    @Column(name = "seats_data")
    private String seatsData;
    @Transient
    private String[][] seatsMatrix;
    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
    private List<FilmBooking> filmBookingList;

    public Showtime() {}

    public Showtime(long showtimeID, Film film, Room room, LocalDateTime showtimeDate, String seatsData, List<FilmBooking> filmBookingList) {
        this.showtimeID = showtimeID;
        this.film = film;
        this.room = room;
        this.showtimeDate = showtimeDate;
        this.seatsData = seatsData;
        this.seatsMatrix = StringUtils.convertTo2DArr(seatsData);
        this.filmBookingList = filmBookingList;
    }

    public Showtime(Film film, Room room, LocalDateTime showtimeDate) {
        this.film = film;
        this.room = room;
        this.showtimeDate = showtimeDate;
        this.seatsData = room.getSeatData();
        this.seatsMatrix = room.getSeatMatrix();
        this.filmBookingList = new ArrayList<>();
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
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getShowtimeDate() {
        return showtimeDate;
    }

    public void setShowtimeDate(LocalDateTime showtimeDate) {
        this.showtimeDate = showtimeDate;
    }

    public String getSeatsData() {
        return seatsData;
    }

    public void setSeatsData(String seatsData) {
        this.seatsData = seatsData;
        this.seatsMatrix = StringUtils.convertTo2DArr(seatsData);
    }

    public String[][] getSeatsMatrix() {
        return seatsMatrix;
    }

    public void setSeatsMatrix(String[][] seatsMatrix) {
        this.seatsMatrix = seatsMatrix;
        this.seatsData = StringUtils.arr2DToString(seatsMatrix);
    }

    public List<FilmBooking> getFilmBookingList() {
        return filmBookingList;
    }

    public void setFilmBookingList(List<FilmBooking> filmBookingList) {
        this.filmBookingList = filmBookingList;
    }

    /**
     * Book seat
     * @param seats is the String array of seats name that user want to book. Example: ["1 2", "2 3", "3 4"]
     */
    public void bookSeats(String[] seats) {
        for (String seat : seats) {
            int row = Integer.parseInt(seat.split(" ")[0]);
            int col = Integer.parseInt(seat.split(" ")[1]);
            this.seatsMatrix[row][col] = "1";
        }
        this.seatsData = StringUtils.arr2DToString(this.seatsMatrix);
    }

    public int countAvailableSeats() {
        int count = 0;
        String[][] seatMatrix = this.getSeatsMatrix();
        for (String[] row : seatMatrix) {
            for (String s : row) {
                if (s.equalsIgnoreCase("0"))
                    count++;
            }
        }
        return count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Showtime) {
            Showtime showtime = (Showtime) obj;
            return this.showtimeID == showtime.getShowtimeID()
            && this.film.equals(showtime.getFilm())
            && this.room.equals(showtime.getRoom())
            && this.showtimeDate.equals(showtime.getShowtimeDate())
            && this.seatsData.equals(showtime.getSeatsData());
        }
        return false;
    }
}
