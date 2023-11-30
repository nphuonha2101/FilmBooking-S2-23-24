package com.filmbooking.services.impls;

import com.filmbooking.dao.FilmBookingDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.User;
import com.filmbooking.services.IFilmBookingServices;
import com.filmbooking.services.IShowtimeServices;

import java.util.List;
import java.util.stream.Collectors;

public class FilmBookingServicesImpl implements IFilmBookingServices {
    private IDAO<FilmBooking> filmBookingDAO;

    private IShowtimeServices showtimeServices;

    public FilmBookingServicesImpl() {
        this.filmBookingDAO = FilmBookingDAOImpl.getInstance();
        this.showtimeServices = new ShowtimeServicesImpl();
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
    public List<FilmBooking> getAllByUser(User user) {
        return this.getAll().stream().filter(filmBooking -> filmBooking.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public void save(FilmBooking filmBooking) {
        Showtime showtime = showtimeServices.getByID(filmBooking.getShowtime().getShowtimeID());
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

    public static void main(String[] args) {
        FilmBookingServicesImpl filmBookingServices = new FilmBookingServicesImpl();
        System.out.println(filmBookingServices.getAll());
    }
}
