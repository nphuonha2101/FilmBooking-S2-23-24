package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.dao.daoDecorators.OffsetDAODecorator;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IShowtimeServices;

import java.util.HashMap;
import java.util.List;

public class ShowtimeServicesImpl implements IShowtimeServices {
    private final DataAccessObjects<Showtime> showtimeDataAccessObjects;

    public ShowtimeServicesImpl() {
        this.showtimeDataAccessObjects = new DataAccessObjects<>(Showtime.class);
    }

    public ShowtimeServicesImpl(HibernateSessionProvider sessionProvider) {
        this.showtimeDataAccessObjects = new DataAccessObjects<>(Showtime.class);
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        showtimeDataAccessObjects.setSessionProvider(sessionProvider);
    }

    @Override
    public long getTotalRecords() {
        return showtimeDataAccessObjects.getTotalRecordRows();
    }

    @Override
    public Showtime getBySlug(String slug) {
        for (Showtime showtime : showtimeDataAccessObjects.getAll().getMultipleResults()) {
            if (showtime.getSlug().equalsIgnoreCase(slug))
                return showtime;
        }
        return null;
    }

    @Override
    public List<Showtime> getByOffset(int offset, int limit) {
        return new OffsetDAODecorator<Showtime>(showtimeDataAccessObjects, offset, limit).getAll().getMultipleResults();
    }

    @Override
    public List<Showtime> getAll() {
        return showtimeDataAccessObjects.getAll().getMultipleResults();
    }

    @Override
    public Showtime getByID(String id) {
        return showtimeDataAccessObjects.getByID(id, true).getSingleResult();
    }

    @Override
    public boolean save(Showtime showtime) {
        return showtimeDataAccessObjects.save(showtime);
    }

    @Override
    public boolean update(Showtime showtime) {
        return showtimeDataAccessObjects.update(showtime);
    }

    @Override
    public boolean delete(Showtime showtime) {
        return showtimeDataAccessObjects.delete(showtime);
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
