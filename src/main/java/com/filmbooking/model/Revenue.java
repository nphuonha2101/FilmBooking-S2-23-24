package com.filmbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Revenue {
    private String filmName;
    private int ticketSold;
    private double filmRevenue;
}
