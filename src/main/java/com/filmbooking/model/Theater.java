package com.filmbooking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
