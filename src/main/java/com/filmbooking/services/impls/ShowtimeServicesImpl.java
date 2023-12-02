package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.ShowtimeDAOImpl;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmBookingServices;
import com.filmbooking.services.IShowtimeServices;

import java.util.HashMap;
import java.util.List;

public class ShowtimeServicesImpl implements IShowtimeServices {
    private final IDAO<Showtime> showtimeDAO;

    public ShowtimeServicesImpl() {
        this.showtimeDAO = ShowtimeDAOImpl.getInstance();
    }

    public ShowtimeServicesImpl(HibernateSessionProvider sessionProvider) {
        this.showtimeDAO = ShowtimeDAOImpl.getInstance();
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        showtimeDAO.setSessionProvider(sessionProvider);
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

    @Override
    public HashMap<Long, String[][]> getShowtimeIDAndSeatMatrix() {
        HashMap<Long, String[][]> result = new HashMap<>();

        for (Showtime showtime : getAll()
        ) {
            String[][] seatsMatrix = showtime.getSeatsMatrix();
            result.put(showtime.getShowtimeID(), seatsMatrix);
        }
        return result;
    }

}
