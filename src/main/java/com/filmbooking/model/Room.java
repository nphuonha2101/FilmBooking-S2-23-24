package com.filmbooking.model;

public class Room {
    private String roomID;
    private String roomName;
    private int seatRows;
    private int seatCols;
    private String[][] seatMatrix;
    private String theaterID;


    public Room(String roomID, String roomName, int seatRows, int seatCols, String[][] seatMatrix, String theaterID) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.seatMatrix = seatMatrix;
        this.theaterID = theaterID;
    }


    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
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
    }

    public int getSeatCols() {
        return seatCols;
    }

    public void setSeatCols(int seatCols) {
        this.seatCols = seatCols;
    }

    public String[][] getSeatMatrix() {
        return seatMatrix;
    }

    public void setSeatMatrix(String[][] seatMatrix) {
        this.seatMatrix = seatMatrix;
    }

    public String getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(String theaterID) {
        this.theaterID = theaterID;
    }
}