package com.filmbooking.services;

import com.filmbooking.model.Showtime;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.HashMap;
import java.util.List;

public interface IShowtimeServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);
    long getTotalRecords();
    List<Showtime> getByOffset(int offset, int limit);
    List<Showtime> getAll();
    Showtime getByID(String id);
    boolean save(Showtime showtime);

    boolean update(Showtime showtime);

    boolean delete(Showtime showtime);

    HashMap<Long, Integer> countAvailableSeats();

    HashMap<Long, String[][]> getShowtimeIDAndSeatMatrix();
}
