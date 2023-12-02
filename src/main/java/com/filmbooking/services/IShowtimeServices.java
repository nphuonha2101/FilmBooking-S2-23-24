package com.filmbooking.services;

import com.filmbooking.model.Showtime;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.HashMap;
import java.util.List;

public interface IShowtimeServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);
    List<Showtime> getAll();
    Showtime getByID(String id);
    void save(Showtime showtime);

    void update(Showtime showtime);

    void delete(Showtime showtime);

    void bookSeats(Showtime showtime, String ...seats);
    HashMap<Long, Integer> countAvailableSeats();

    HashMap<Long, String[][]> getShowtimeIDAndSeatMatrix();
}
