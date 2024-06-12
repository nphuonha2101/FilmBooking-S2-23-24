package com.filmbooking.model;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.repository.ShowtimeRepository;
import com.filmbooking.repository.TheaterRepository;
import com.filmbooking.utils.StringUtils;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@TableName("rooms")
@TableIdName("room_id")
@IdAutoIncrement
public class Room implements IModel {
    public static final String TABLE_NAME = "rooms";

    @Expose
    private long roomID;
    @Expose
    private String roomName;
    @Expose
    private int seatRows;
    @Expose
    private int seatCols;
    private String[][] seatMatrix;
    @Expose
    private String seatData;
    @Expose
    private Theater theater;
    private List<Showtime> showtimeList;
    @Expose
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

    /**
     * Use for retrieve data from database
     */
    public Room(long roomID, String roomName, int seatRows, int seatCols, String seatData, Theater theater, String slug) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.seatData = seatData;
        this.seatMatrix = StringUtils.convertTo2DArr(seatData);
        this.theater = theater;
        this.slug = slug;
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

    public List<Showtime> getShowtimeList() {
        this.showtimeList = new ShowtimeRepository(Showtime.class).selectAllByRoomId(this.roomID);
        return showtimeList;
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

    @Override
    public Object getIdValue() {
        return this.roomID;
    }

}