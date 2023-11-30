package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.ShowtimeDAOImpl;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmBookingServices;
import com.filmbooking.services.IShowtimeServices;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowtimeServicesImpl implements IShowtimeServices {
    private final IDAO<Showtime> showtimeDAO;
    private final IFilmBookingServices filmBookingServices;

    public ShowtimeServicesImpl() {
        this.showtimeDAO = ShowtimeDAOImpl.getInstance();
        filmBookingServices = new FilmBookingServicesImpl();
//        hideOldShowtime();
    }

    @Override
    public void openSession() {
        showtimeDAO.openSession();
    }

    @Override
    public void closeSession() {
        showtimeDAO.closeSession();
    }

    @Override
    public List<Showtime> getAll() {
        return showtimeDAO.getAll();
    }

    @Override
    public Showtime getByID(String id) {
        return showtimeDAO.getByID(id);
    }

    @Override
    public List<Showtime> getByFilmID(String filmID) {
        List<Showtime> result = new ArrayList<>();
        for (Showtime showtime : this.getAll()
        ) {
//            if (showtime.getFilm().getFilmID().equalsIgnoreCase(filmID))
//                result.add(showtime);
        }
        return result;
    }

    @Override
    public HashMap<String, Showtime> getShowtimeAndShowtimeID() {
//        return this.getObjectAndObjectID(this.getAll());
        return null;
    }

    @Override
    public void save(Showtime showtime) {
        showtimeDAO.save(showtime);
    }

    @Override
    public void update(Showtime showtime) {
        showtimeDAO.update(showtime);
    }

    @Override
    public void delete(Showtime showtime) {
        showtimeDAO.delete(showtime);
    }

    @Override
    public void bookSeats(Showtime showtime, String[] seats) {
        showtime.bookSeats(seats);
        update(showtime);
    }

    @Override
    public HashMap<Long, Integer> countAvailableSeats() {
        HashMap<Long, Integer> result = new HashMap<>();

        for (Showtime showtime : getAll()
        ) {
            int availableSeats = showtime.countAvailableSeats();
            result.put(showtime.getShowtimeID(), availableSeats);
        }
        return result;
    }

}
