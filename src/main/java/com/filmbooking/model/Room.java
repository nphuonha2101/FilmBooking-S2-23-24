package com.filmbooking.model;

import com.filmbooking.utils.StringUtils;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Setter
    @Getter
    @Expose
    @Column(name = "room_id", updatable = false, insertable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomID;
    @Getter
    @Column(name = "room_name")
    @Expose
    private String roomName;
    @Getter
    @Expose
    @Column(name = "seat_rows")
    private int seatRows;
    @Getter
    @Expose
    @Column(name = "seat_cols")
    private int seatCols;
    @Setter
    @Transient
    private String[][] seatMatrix;
    @Setter
    @Getter
    @Expose
    @Column(name = "seats_data")
    private String seatData;
    @Expose
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @Setter
    @Getter
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Showtime> showtimeList;
    @Setter
    @Getter
    @Expose
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


    public void setRoomName(String roomName) {
        this.roomName = roomName;
        this.slug = StringUtils.createSlug(this.roomName + " " + this.theater.getTheaterName(), 50);
    }

    public void setSeatRows(int seatRows) {
        this.seatRows = seatRows;
        generateSeatsData();
    }

    public void setSeatCols(int seatCols) {
        this.seatCols = seatCols;
        generateSeatsData();
    }

    public String[][] getSeatMatrix() {
        return StringUtils.convertTo2DArr(this.seatData);
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