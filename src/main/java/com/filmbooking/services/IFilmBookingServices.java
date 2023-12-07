package com.filmbooking.services;

import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IFilmBookingServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);
    List<FilmBooking> getAll();
    FilmBooking getByFilmBookingID(String id);
    List<FilmBooking> getAllByUser(User user);
    boolean save(FilmBooking filmBooking);
    boolean update(FilmBooking filmBooking);
    boolean delete(FilmBooking filmBooking);
}
