package com.filmbooking.services.impls;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractCRUDServices;
import com.filmbooking.services.IShowtimeServices;

public class ShowtimeServicesImpl extends AbstractCRUDServices<Showtime> implements IShowtimeServices {

    public ShowtimeServicesImpl(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO = new DataAccessObjects<>(Showtime.class);
        this.setSessionProvider(sessionProvider);
    }

    public ShowtimeServicesImpl() {
        this.decoratedDAO = new DataAccessObjects<>(Showtime.class);
    }

    @Override
    public String getTableName() {
        return Showtime.TABLE_NAME;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public Showtime getByID(String id) {
        if (!Objects.equals(id, "null"))
            return this.decoratedDAO.getByID(id, true);
        else
            throw new RuntimeException("ID must not be null");
    }

    /**
     * Get a map storing showtimeID and its available seats
     *
     * @return HashMap<Long, Integer> showtimeID and its available seats
     */
    public Map<Long, Integer> getAvailableSeatsByShowtimeId() {
        HashMap<Long, Integer> result = new HashMap<>();

        for (Showtime showtime : getAll().getMultipleResults()
        ) {
            int availableSeats = showtime.countAvailableSeats();
            result.put(showtime.getShowtimeID(), availableSeats);
        }
        return result;
    }

    /**
     * Get a map storing showtimeID and its seat matrix
     *
     * @return HashMap<Long, String [ ] [ ]> showtimeID and its seat matrix
     */
    public HashMap<Long, String[][]> getShowtimeIDAndSeatMatrix() {
        HashMap<Long, String[][]> result = new HashMap<>();

        for (Showtime showtime : getAll().getMultipleResults()
        ) {
            String[][] seatsMatrix = showtime.getSeatsMatrix();
            result.put(showtime.getShowtimeID(), seatsMatrix);
        }
        return result;
    }

    @Override
    public User newUser(String username, String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newUser'");
    }


}
