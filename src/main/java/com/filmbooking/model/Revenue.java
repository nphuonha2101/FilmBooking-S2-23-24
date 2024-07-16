package com.filmbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Revenue implements IModel {
    private String revenueName;
    private int ticketSold;
    private double totalRevenue;

    @Override
    public Object getIdValue() {
        return null;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return null;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return null;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {

    }

    @Override
    public void setUpdatedAt(LocalDateTime updatedAt) {

    }
}
