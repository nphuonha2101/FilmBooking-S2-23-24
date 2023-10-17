package com.filmbooking.model;

import com.filmbooking.utils.ConvertStringtoArrayUtils;

public class Room {
    private String roomID;
    private String roomName;
    private int seatRows;
    private int seatCols;
    private String[][] seatMatrix;
    private String seatData;
    private String theaterID;


    public Room(String roomID, String roomName, int seatRows, int seatCols, String[][] seatMatrix, String theaterID) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.seatMatrix = seatMatrix;
        this.seatData = ConvertStringtoArrayUtils.arrToString(seatMatrix);
        this.theaterID = theaterID;
    }

    public Room(String roomID, String roomName, int seatRows, int seatCols, String seatData, String theaterID) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.seatData = seatData;
        this.seatMatrix = ConvertStringtoArrayUtils.convertTo2DArr(seatData);
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

    public String getSeatData() {
        return seatData;
    }

    public void setSeatData(String seatData) {
        this.seatData = seatData;
    }
}