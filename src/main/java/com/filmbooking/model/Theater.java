package com.filmbooking.model;

public class Theater {
    private String theaterID;
    private String theaterName;
    private String taxCode;
    private String theaterAddress;

    public Theater(String theaterID, String theaterName, String taxCode, String theaterAddress) {
        this.theaterID = theaterID;
        this.theaterName = theaterName;
        this.taxCode = taxCode;
        this.theaterAddress = theaterAddress;
    }

    public Theater(String theaterName, String taxCode, String theaterAddress) {
        this.theaterName = theaterName;
        this.taxCode = taxCode;
        this.theaterAddress = theaterAddress;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
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
