package com.filmbooking.model;

import com.filmbooking.utils.StringUtils;

import java.time.LocalDateTime;

public class Showtime {
    private String showtimeID;
    private String filmID;
    private String roomID;
    private LocalDateTime showtimeDate;
    private String seatsData;
    private String[][] seatsMatrix;

    public Showtime(String showtimeID, String filmID, String roomID, LocalDateTime showtimeDate, String seatsData) {
        this.showtimeID = showtimeID;
        this.filmID = filmID;
        this.roomID = roomID;
        this.showtimeDate = showtimeDate;
        this.seatsData = seatsData;
        this.seatsMatrix = StringUtils.convertTo2DArr(seatsData);
    }

    public Showtime(String filmID, Room room, LocalDateTime showtimeDate) {
        this.filmID = filmID;
        this.roomID = room.getRoomID();
        this.showtimeDate = showtimeDate;
        this.seatsData = room.getSeatData();
        this.seatsMatrix = room.getSeatMatrix();
    }

    public String getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(String showtimeID) {
        this.showtimeID = showtimeID;
    }

    public String getFilmID() {
        return filmID;
    }

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
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
}
