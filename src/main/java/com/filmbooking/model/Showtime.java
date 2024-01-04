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
     * Book seat
     *
     * @param seats is the String array of seats name that user want to book. Example: ["1 2", "2 3", "3 4"]
     */
    public synchronized boolean bookSeats(String[] seats) {
        String[][] seatsMatrix = StringUtils.convertTo2DArr(this.seatsData);
        for (String seat : seats) {
            int row = Integer.parseInt(seat.split(" ")[0]);
            int col = Integer.parseInt(seat.split(" ")[1]);

            if (seatsMatrix[row][col].equals("1"))
                return false;

            seatsMatrix[row][col] = "1";
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
