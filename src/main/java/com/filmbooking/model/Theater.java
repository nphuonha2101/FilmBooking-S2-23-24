package com.filmbooking.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "theaters")
public class Theater {
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
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Room> roomList;

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
}
