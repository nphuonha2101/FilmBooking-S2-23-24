package com.filmbooking.services.impls;

import com.filmbooking.dao.GenericDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IShowtimeServices;

import java.util.HashMap;
import java.util.List;

public class ShowtimeServicesImpl implements IShowtimeServices {
    private final IDAO<Showtime> showtimeDAO;

    public ShowtimeServicesImpl() {
        this.showtimeDAO = new GenericDAOImpl<>(Showtime.class);
    }

    public ShowtimeServicesImpl(HibernateSessionProvider sessionProvider) {
        this.showtimeDAO = new GenericDAOImpl<>(Showtime.class);
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        showtimeDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public long getTotalRecords() {
        return showtimeDAO.getTotalRecords();
    }

    @Override
    public Showtime getBySlug(String slug) {
        for (Showtime showtime : showtimeDAO.getAll()) {
            if (showtime.getSlug().equalsIgnoreCase(slug))
                return showtime;
        }
        return null;
    }

    @Override
    public List<Showtime> getByOffset(int offset, int limit) {
        return showtimeDAO.getByOffset(offset, limit);
    }

    @Override
    public List<Showtime> getAll() {
        return showtimeDAO.getAll();
    }

    @Override
    public Showtime getByID(String id) {
        return showtimeDAO.getByID(id, true);
    }

    @Override
    public boolean save(Showtime showtime) {
        return showtimeDAO.save(showtime);
    }

    @Override
    public boolean update(Showtime showtime) {
        return showtimeDAO.update(showtime);
    }

    @Override
    public boolean delete(Showtime showtime) {
        return showtimeDAO.delete(showtime);
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
