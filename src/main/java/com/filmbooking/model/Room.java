package com.filmbooking.model;

import com.filmbooking.utils.StringUtils;

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
        this.seatData = StringUtils.arrToString(seatMatrix);
        this.theaterID = theaterID;
    }

    public Room(String roomID, String roomName, int seatRows, int seatCols, String seatData, String theaterID) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.seatData = seatData;
        this.seatMatrix = StringUtils.convertTo2DArr(seatData);
        this.theaterID = theaterID;
    }

    public Room( String roomName, int seatRows, int seatCols, String theaterID) {
        this.roomName = roomName;
        this.seatRows = seatRows;
        this.seatCols = seatCols;
        this.theaterID = theaterID;

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

    public int countAvailableSeats() {
        int count = 0;
        String[][] seatMatrix = this.getSeatMatrix();
        for (String[] row : seatMatrix) {
            for (String s : row) {
                if (s.equalsIgnoreCase("0"))
                    count++;
            }
        }
        return count;
    }
}