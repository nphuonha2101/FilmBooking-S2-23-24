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
            this.roomList = new RoomRepository().selectAllByTheaterId(this.theaterID);
        return roomList;
    }

    @Override
    public Object getIdValue() {
        return this.theaterID;
    }

}
