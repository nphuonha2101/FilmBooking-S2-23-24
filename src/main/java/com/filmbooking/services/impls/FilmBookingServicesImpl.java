package com.filmbooking.services.impls;

import com.filmbooking.dao.FilmBookingDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.services.IFilmBookingServices;

import java.util.List;

public class FilmBookingServicesImpl implements IFilmBookingServices {
    private IDAO<FilmBooking> filmBookingDAO;

    public FilmBookingServicesImpl() {
        filmBookingDAO = new FilmBookingDAOImpl();
    }

    @Override
    public List<FilmBooking> getAll() {
        return filmBookingDAO.getAll();
    }

    @Override
    public FilmBooking getByFilmBookingID(String id) {
        return filmBookingDAO.getByID(id);
    }

    @Override
    public void save(FilmBooking filmBooking) {
        filmBookingDAO.save(filmBooking);
    }

    @Override
    public void update(FilmBooking filmBooking) {
        filmBookingDAO.update(filmBooking);
    }

    @Override
    public void delete(FilmBooking filmBooking) {
        filmBookingDAO.delete(filmBooking);
    }
}
