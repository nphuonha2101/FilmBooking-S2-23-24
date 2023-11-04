package com.filmbooking.services.impls;

import com.filmbooking.dao.FilmBookingDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmBookingServices;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.IShowtimeViewServices;

import java.util.List;

public class FilmBookingServicesImpl implements IFilmBookingServices {
    private final IDAO<FilmBooking> filmBookingDAO;

    private final IShowtimeServices showtimeServices;

    public FilmBookingServicesImpl() {
        filmBookingDAO = new FilmBookingDAOImpl();
        showtimeServices = new ShowtimeServicesImpl();
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
        Showtime showtime = showtimeServices.getByID(filmBooking.getShowtimeID());
        showtimeServices.bookSeats(showtime, filmBooking.getSeats());
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
