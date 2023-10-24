package com.filmbooking.model.view;

public class RoomView {
    private String roomID;
    private String roomName;
    private int seatRows;
    private int seatCols;
    private String theaterName;

    public RoomView(String roomID, String roomName, int seatRows, int seatCols, String theaterName) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.theaterName = theaterName;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getSeatRows() {
        return seatRows;
    }

    public int getSeatCols() {
        return seatCols;
    }

    public String getTheaterName() {
        return theaterName;
    }
}
