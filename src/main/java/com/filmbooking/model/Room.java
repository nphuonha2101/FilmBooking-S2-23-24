package com.filmbooking.model;

import com.filmbooking.utils.StringUtils;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Column(name = "room_id", updatable = false, insertable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomID;
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "seat_rows")
    private int seatRows;
    @Column(name = "seat_cols")
    private int seatCols;
    @Transient
    private String[][] seatMatrix;
    @Column(name = "seats_data")
    private String seatData;
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Showtime> showtimeList;
    @Column(name = "slug")
    private String slug;

    public Room() {
    }

    /**
     * Create new room constructor and send to database
     */
    public Room(String roomName, int seatRows, int seatCols, Theater theater) {
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.theater = theater;
        this.slug = StringUtils.createSlug(this.roomName + " " + this.theater.getTheaterName(), 50);
        generateSeatsData();
    }

    private void generateSeatsData() {
        this.seatMatrix = new String[seatRows][seatCols];
        this.seatData = "";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < seatRows; i++) {
            for (int j = 0; j < seatCols; j++) {
                stringBuilder.append("0");
                this.seatMatrix[i][j] = "0";

            }
            stringBuilder.append(" ");
        }
        this.seatData = stringBuilder.toString().trim();
    }


    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
        this.slug = StringUtils.createSlug(this.roomName + " " + this.theater.getTheaterName(), 50);
    }

    public int getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(int seatRows) {
        this.seatRows = seatRows;
        generateSeatsData();
    }

    public int getSeatCols() {
        return seatCols;
    }

    public void setSeatCols(int seatCols) {
        this.seatCols = seatCols;
        generateSeatsData();
    }

    public String[][] getSeatMatrix() {
        return StringUtils.convertTo2DArr(this.seatData);
    }

    public void setSeatMatrix(String[][] seatMatrix) {
        this.seatMatrix = seatMatrix;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public List<Showtime> getShowtimeList() {
        return showtimeList;
    }

    public void setShowtimeList(List<Showtime> showtimeList) {
        this.showtimeList = showtimeList;
    }

    public String getSeatData() {
        return seatData;
    }

    public void setSeatData(String seatData) {
        this.seatData = seatData;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Room room) {
            return this.roomID == room.getRoomID()
                    && this.roomName.equals(room.getRoomName())
                    && this.seatRows == room.getSeatRows()
                    && this.seatCols == room.getSeatCols()
                    && this.seatData.equals(room.getSeatData())
                    && this.theater.equals(room.getTheater());
        }
        return false;
    }
}