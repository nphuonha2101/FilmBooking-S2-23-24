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
    private final IDAO<FilmBooking> filmBookingDAO;


    public FilmBookingServicesImpl() {
        this.filmBookingDAO = FilmBookingDAOImpl.getInstance();
    }

    @Override
    public List<FilmBooking> getAll() {
        List<FilmBooking> result;
        filmBookingDAO.openSession();
        result = filmBookingDAO.getAll();
        filmBookingDAO.closeSession();
        return result;
    }

    @Override
    public FilmBooking getByFilmBookingID(String id) {

        filmBookingDAO.openSession();
        FilmBooking result = filmBookingDAO.getByID(id);
        filmBookingDAO.closeSession();
        return result;

    }

    @Override
    public List<FilmBooking> getAllByUser(User user) {
        return this.getAll().stream().filter(filmBooking -> filmBooking.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public void save(FilmBooking filmBooking) {
        filmBookingDAO.openSession();
        filmBookingDAO.save(filmBooking);
        filmBookingDAO.closeSession();
    }

    @Override
    public void update(FilmBooking filmBooking) {
        filmBookingDAO.openSession();
        filmBookingDAO.update(filmBooking);
        filmBookingDAO.closeSession();
    }

    @Override
    public void delete(FilmBooking filmBooking) {
        filmBookingDAO.openSession();
        filmBookingDAO.delete(filmBooking);
        filmBookingDAO.closeSession();
    }

    public static void main(String[] args) {
        FilmBookingServicesImpl filmBookingServices = new FilmBookingServicesImpl();
        System.out.println(filmBookingServices.getAll());
    }
}
