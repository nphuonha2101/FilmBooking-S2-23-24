package com.filmbooking.model;

import com.filmbooking.utils.StringUtils;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Column(name = "room_id")
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
    private Theater theater;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Showtime> showtimeList;

    public Room() {}

    /**
     * Using this constructor to display room information
     */
    public Room(long roomID, String roomName, int seatRows, int seatCols, String[][] seatMatrix, Theater theater,
                List<Showtime> showtimeList) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.seatMatrix = seatMatrix;
        this.seatData = StringUtils.arr2DToString(seatMatrix);
        this.theater = theater;
        this.showtimeList = showtimeList;
    }

    /**
     * Get from database constructor
     */
    public Room(long roomID, String roomName, int seatRows, int seatCols, String seatData, Theater theater,
                List<Showtime> showtimeList) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.seatData = seatData;
        this.seatMatrix = StringUtils.convertTo2DArr(seatData);
        this.theater = theater;
        this.showtimeList = showtimeList;
    }

    /**
     * Create new room constructor and send to database
     */
    public Room(String roomName, int seatRows, int seatCols, Theater theater) {
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.theater = theater;
        generateSeatsData();
    }

    private void generateSeatsData() {
        this.seatMatrix = new String[seatRows][seatCols];
        this.seatData = "";

        for (int i = 0; i < seatRows; i++) {
            for (int j = 0; j < seatCols; j++) {
                this.seatData += "0";
                this.seatMatrix[i][j] = "0";

            }
            this.seatData += " ";
        }
        this.seatData = this.seatData.trim();
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
        return seatMatrix;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Room) {
            Room room = (Room) obj;
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