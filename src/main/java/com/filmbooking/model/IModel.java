package com.filmbooking.model;

import java.time.LocalDateTime;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public interface IModel {
    Object getIdValue();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);
}
