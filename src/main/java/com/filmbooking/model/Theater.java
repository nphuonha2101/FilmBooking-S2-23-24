package com.filmbooking.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "theaters")
public class Theater {
    @Column(name = "theater_id", insertable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long theaterID;
    @Column(name = "theater_name")
    private String theaterName;
    @Column(name = "tax_code")
    private String taxCode;
    @Column(name = "theater_address")
    private String theaterAddress;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    List<Room> roomList;

    public Theater() {}

    public Theater(long theaterID, String theaterName, String taxCode, String theaterAddress, List<Room> roomList) {
        this.theaterID = theaterID;
        this.theaterName = theaterName;
        this.taxCode = taxCode;
        this.theaterAddress = theaterAddress;
        this.roomList = roomList;
    }

    public Theater(String theaterName, String taxCode, String theaterAddress, List<Room> roomList) {
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

    public long getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(long theaterID) {
        this.theaterID = theaterID;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Theater theater) {
            return this.theaterID == theater.getTheaterID()
                    && this.theaterName.equals(theater.getTheaterName())
                    && this.taxCode.equals(theater.getTaxCode())
                    && this.theaterAddress.equals(theater.getTheaterAddress())
                    && this.roomList.equals(theater.getRoomList());
        }
        return false;
    }
}
