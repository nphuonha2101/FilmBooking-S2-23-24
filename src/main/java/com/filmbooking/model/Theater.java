package com.filmbooking.model;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
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
@AllArgsConstructor
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

    public Theater() {}
    public Theater(long theaterID){
        this.theaterID = theaterID;
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

    @Override
    public Object getIdValue() {
        return this.theaterID;
    }

    public Map<String, Object> mapToRow() {
        return Map.of(
                "theater_id", this.theaterID,
                "theater_name", this.theaterName,
                "tax_code", this.taxCode,
                "theater_address", this.theaterAddress
        );
    }
}
