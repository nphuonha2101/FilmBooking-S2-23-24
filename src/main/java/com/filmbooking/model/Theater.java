package com.filmbooking.model;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.repository.RoomRepository;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@TableName("theaters")
@TableIdName("theater_id")
@IdAutoIncrement
public class Theater implements IModel {
    public static final String TABLE_NAME = "theaters";

    @Expose
    private long theaterID;
    @Expose
    private String theaterName;
    @Expose
    private String taxCode;
    @Expose
    private String theaterAddress;

    private List<Room> roomList;

    public Theater(long theaterID, String theaterName, String taxCode, String theaterAddress) {
        this.theaterID = theaterID;
        this.theaterName = theaterName;
        this.taxCode = taxCode;
        this.theaterAddress = theaterAddress;
    }

    public Theater(long theaterID) {
        this.theaterID = theaterID;
    }

    public List<Room> getRoomList() {
        if (this.roomList == null)
            this.roomList = new RoomRepository(Room.class).selectAllByTheaterId(this.theaterID);
        return roomList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Theater theater) {
            return this.theaterID == theater.getTheaterID()
                    && this.theaterName.equals(theater.getTheaterName())
                    && this.taxCode.equals(theater.getTaxCode())
                    && this.theaterAddress.equals(theater.getTheaterAddress());
        }
        return false;
    }

    @Override
    public Object getIdValue() {
        return this.theaterID;
    }

}
