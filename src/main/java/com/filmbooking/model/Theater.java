package com.filmbooking.model;

public class Theater {
    private String theaterID;
    private String theaterName;
    private String taxNumber;
    private String theaterAddress;

    public Theater(String theaterID, String theaterName, String taxNumber, String theaterAddress) {
        this.theaterID = theaterID;
        this.theaterName = theaterName;
        this.taxNumber = taxNumber;
        this.theaterAddress = theaterAddress;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getTheaterAddress() {
        return theaterAddress;
    }

    public void setTheaterAddress(String theaterAddress) {
        this.theaterAddress = theaterAddress;
    }

    public String getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(String theaterID) {
        this.theaterID = theaterID;
    }
}
