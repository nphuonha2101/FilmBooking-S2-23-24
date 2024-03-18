package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.IFilmBookingServices;

import java.util.List;
import java.util.stream.Collectors;

public class FilmBookingServicesImpl implements IFilmBookingServices {
    private final DataAccessObjects<FilmBooking> filmBookingDataAccessObjects;

    public FilmBookingServicesImpl(HibernateSessionProvider sessionProvider) {
        this.filmBookingDataAccessObjects = new DataAccessObjects<>(FilmBooking.class);
        setSessionProvider(sessionProvider);
    }

    public FilmBookingServicesImpl() {
        this.filmBookingDataAccessObjects = new DataAccessObjects<>(FilmBooking.class);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        filmBookingDataAccessObjects.setSessionProvider(sessionProvider);
    }

    @Override
    public List<FilmBooking> getAll() {
        return filmBookingDataAccessObjects.getAll().getMultipleResults();
    }

    @Override
    public FilmBooking getByFilmBookingID(String id) {
        return filmBookingDataAccessObjects.getByID(id, true).getSingleResult();
    }

    @Override
    public List<FilmBooking> getAllByUser(User user) {
        return this.getAll().stream().filter(filmBooking -> filmBooking.getUser().getUsername().equals(user.getUsername())).collect(Collectors.toList());
    }

    @Override
    public boolean save(FilmBooking filmBooking) {
        return filmBookingDataAccessObjects.save(filmBooking);
    }

    @Override
    public boolean update(FilmBooking filmBooking) {
        return filmBookingDataAccessObjects.update(filmBooking);
    }

    @Override
    public boolean delete(FilmBooking filmBooking) {
        return filmBookingDataAccessObjects.delete(filmBooking);
    }


}
