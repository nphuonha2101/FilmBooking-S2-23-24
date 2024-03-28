package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.dao.daoDecorators.OffsetDAODecorator;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.AbstractServices;
import com.filmbooking.services.IServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowtimeServicesImpl extends AbstractServices<Showtime> {

    public ShowtimeServicesImpl(HibernateSessionProvider sessionProvider) {
        super.decoratedDAO = new DataAccessObjects<>(Showtime.class);
        super.setSessionProvider(sessionProvider);
    }

    @Override
    public Showtime getByID(String id) {
        return this.decoratedDAO.getByID(id, true);
    }

    /**
     * Get a map storing showtimeID and its available seats
     * @return HashMap<Long, Integer> showtimeID and its available seats
     */
    public HashMap<Long, Integer> countAvailableSeats() {
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
     * @return HashMap<Long, String[][]> showtimeID and its seat matrix
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


}
