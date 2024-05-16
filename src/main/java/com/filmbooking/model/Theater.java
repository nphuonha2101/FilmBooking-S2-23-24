package com.filmbooking.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = Theater.TABLE_NAME)
public class Theater implements IModel {
    @Transient
    public static final String TABLE_NAME = "theaters";

    @Expose
    @Column(name = "theater_id", insertable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long theaterID;
    @Expose
    @Column(name = "theater_name")
    private String theaterName;
    @Expose
    @Column(name = "tax_code")
    private String taxCode;
    @Expose
    @Column(name = "theater_address")
    private String theaterAddress;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Room> roomList;

    public Theater() {}

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
    public String getStringID() {
        return String.valueOf(this.theaterID);
    }
}
